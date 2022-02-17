#import <Foundation/Foundation.h>
#import "NativeCallProxy.h"

@implementation FrameworkLibAPI

id<NativeCallsProtocol> api = NULL;
+(void) registerAPIforNativeCalls:(id<NativeCallsProtocol>) aApi
{
    api = aApi;
}


@end

extern "C"
{
    void sendMessageToMobileApp(const char* message)
    {
        return [api sendMessageToMobileApp:[NSString stringWithUTF8String:message]];
    }

    void OnUnityLoaded(){
        return [api OnUnityLoaded];
    }
    
    void OnGameCallBack(const char* gameId, const char* hasCompleted, const char* playTime){
        return [api OnGameCallBack:[NSString stringWithUTF8String:gameId]: [NSString stringWithUTF8String:hasCompleted]: [NSString stringWithUTF8String:playTime]];
    }
}
