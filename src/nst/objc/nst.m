#import <Cocoa/Cocoa.h>

typedef  void (*callback)(char *);

static callback c;

void registerCallback(callback myc) {
  c = myc;
}

@interface NSTDelegate : NSObject  <NSTouchBarDelegate>
    - (void)button:(id)sender;
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
    } else if ([identifier isEqualToString:@"button"]) {
        NSButton *theButton =  [NSButton buttonWithTitle:@"MyButton" target:self action:@selector(button:)];

        NSCustomTouchBarItem *customItemForButton =
                [[NSCustomTouchBarItem alloc] initWithIdentifier:@"button"];
        customItemForButton.view = theButton;

        // We want this label to always be visible no matter how many items are in the NSTouchBar instance.
        customItemForButton.visibilityPriority = NSTouchBarItemPriorityHigh;

        return customItemForButton;
    }
    return nil;
}

- (void)button:(id)sender {
    (*c)("button");
}

@end
