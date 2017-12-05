#import <Cocoa/Cocoa.h>


@interface NSTDelegate : NSObject  <NSTouchBarDelegate>


@end


@implementation NSTDelegate


// This gets called while the NSTouchBar is being constructed, for each NSTouchBarItem to be created.
- (nullable NSTouchBarItem *)touchBar:(NSTouchBar *)touchBar makeItemForIdentifier:(NSTouchBarItemIdentifier)identifier
{
    if ([identifier isEqualToString:@"label"])
    {
        NSTextField *theLabel = [NSTextField labelWithString:NSLocalizedString(@"MyLabel", @"")];

        NSCustomTouchBarItem *customItemForLabel =
                [[NSCustomTouchBarItem alloc] initWithIdentifier:@"label"];
        customItemForLabel.view = theLabel;

        // We want this label to always be visible no matter how many items are in the NSTouchBar instance.
        customItemForLabel.visibilityPriority = NSTouchBarItemPriorityHigh;

        return customItemForLabel;
    }

    return nil;
}

@end
