#import <Foundation/Foundation.h>

@protocol NativeCallsProtocol
@required
- (void) sendMessageToMobileApp:(NSString*)message;
- (void) OnUnityLoaded;
- (void) OnGameCallBack:(NSString*)gameId :(NSString*)hasCompleted :(NSString*)playTime;//(NSString* gameId, NSString* hasCompleted, NSString* playTime);
//(gameId: String, hasCompleted: String, scores: String, playTime: String)
// other methods
@end

__attribute__ ((visibility("default")))
@interface FrameworkLibAPI : NSObject
+(void) registerAPIforNativeCalls:(id<NativeCallsProtocol>) aApi;

@end
