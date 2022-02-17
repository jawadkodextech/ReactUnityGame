//
//  UnityChecks.swift
//  unitygamecheck
//
//  Created by Afrah on 2/11/22.
//

import Foundation
import UIKit
import UnityFramework


class UnityCheck:UIResponder,NativeCallsProtocol{//UIApplicationDelegate
  static let shared = UnityCheck()
  
  func onUnityLoaded(){
    UnityCheck.shared.sendMessage("ReactUnityBridge", methodName: "LoadGame", message: "{gameId:232, assetBundleURL: https://www.abjadiayt.com/game1, level:5, scores:100, sessionTime:30 }")
  }
  
  func onGameCallBack(_ gameId: String!, _ hasCompleted: String!, _ playTime: String!) {
    if(gameId == "1122"){
      unloadWindow()
    }else{
      print("gameid \(gameId)")
    }
  }
  
  override init() {
    super.init()
    
//    NSClassFromString("FrameworkLibAPI")?.registerAPIforNativeCalls(self)
  }

  func sendMessage(toMobileApp message: String) {
         print(message)
    }
  // The structure for Unity messages
  private struct UnityMessage {
      let objectName: String?
      let methodName: String?
      let messageBody: String?
  }

  private var cachedMessages = [UnityMessage]() // Array of cached messages

  private let dataBundleId: String = "com.unity3d.framework"
  private let frameworkPath: String = "/Frameworks/UnityFramework.framework"
  
  private var ufw : UnityFramework?
  private var hostMainWindow : UIWindow?
  
  private var isInitialized: Bool {
    ufw?.appController() != nil
  }
  
  func show() {
    if isInitialized {
      showWindow()
    } else {
      initWindow()
    }
  }
  
  func setHostMainWindow(_ hostMainWindow: UIWindow?) {
    self.hostMainWindow = hostMainWindow
  }
  private func initWindow() {
    if isInitialized {
      showWindow()
      return
    }
    
    guard let ufw = loadUnityFramework() else {
      print("ERROR: Was not able to load Unity")
      return unloadWindow()
    }
//    let className = NSClassFromString("FrameworkLibAPI")
//    print("is load \(className)" )
//    className?.registerAPIforNativeCalls(self)
    self.ufw = ufw
    ufw.setDataBundleId(dataBundleId)
    ufw.register(self)
    ufw.runEmbedded(
      withArgc: CommandLine.argc,
      argv: CommandLine.unsafeArgv,
      appLaunchOpts: nil
    )
//    ufw.regist
    let classNameA = NSClassFromString("FrameworkLibAPI")
    print("is load \(classNameA)" )
    classNameA?.registerAPIforNativeCalls(self)
    sendCachedMessages() // Added this line
  }
  
  private func showWindow() {
    if isInitialized {
      ufw?.showUnityWindow()
      sendCachedMessages() // Added this line
    }
  }
  
  private func unloadWindow() {
    if isInitialized {
      cachedMessages.removeAll() // Added this line
      ufw?.unloadApplication()
    }
  }
  private func loadUnityFramework() -> UnityFramework? {
    let bundlePath: String = Bundle.main.bundlePath + frameworkPath
    
    let bundle = Bundle(path: bundlePath)
    if bundle?.isLoaded == false {
      bundle?.load()
    }
    
    let ufw = bundle?.principalClass?.getInstance()
    if ufw?.appController() == nil {
      let machineHeader = UnsafeMutablePointer<MachHeader>.allocate(capacity: 1)
      machineHeader.pointee = _mh_execute_header
      
      ufw?.setExecuteHeader(machineHeader)
    }
    return ufw
  }
  
  func sendMessage(
    _ objectName: String,
    methodName: String,
    message: String
  ) {
    let msg: UnityMessage = UnityMessage(
      objectName: objectName,
      methodName: methodName,
      messageBody: message
    )
    
    // Send the message right away if Unity is initialized, else cache it
    if isInitialized {
      ufw?.sendMessageToGO(
        withName: msg.objectName,
        functionName: msg.methodName,
        message: msg.messageBody
      )
    } else {
      cachedMessages.append(msg)
    }
  }
  
  private func sendCachedMessages() {
    if cachedMessages.count >= 0 && isInitialized {
      for msg in cachedMessages {
        ufw?.sendMessageToGO(
          withName: msg.objectName,
          functionName: msg.methodName,
          message: msg.messageBody
        )
      }
      
      cachedMessages.removeAll()
    }
  }
  
}


extension UnityCheck: UnityFrameworkListener {

    func unityDidUnload(_ notification: Notification!) {
        ufw?.unregisterFrameworkListener(self)
        ufw = nil
        hostMainWindow?.makeKeyAndVisible()
    }
}
