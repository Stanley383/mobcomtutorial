#import "AddRoleTVC.h"

@implementation AddRoleTVC
@synthesize delegate;

- (void)viewDidUnload
{
    [super viewDidUnload];
}
- (IBAction)save:(id)sender
{
    NSLog(@"Telling the AddRoleTVC Delegate that Save was tapped on the AddRoleTVC");
    [self.delegate theSaveButtonOnTheAddRoleTVCWasTapped:self];
}
@end