#import <React/RCTBridgeDelegate.h>
#import <UIKit/UIKit.h>
#include <UnityFramework/UnityFramework.h>
#define nullptr ((void*)0)
//#include <UnityFramework/NativeCallProxy.h>
//#define nullptr ((void*)0)
@interface AppDelegate : UIResponder <UIApplicationDelegate, RCTBridgeDelegate,UnityFrameworkListener>//NativeCallsProtocol
@property UnityFramework* ufw;
@property (nonatomic, strong) UIWindow *window;
- (void)ShowMainView;
- (bool)unityIsInitialized;
- (void)initUnity;
- (void)OnUnityLoaded;
@end
//int gArgc = 0;
//char** gArgv = nullptr;
NSDictionary* appLaunchOpts;
//AppDelegate* hostDelegate = NULL;
