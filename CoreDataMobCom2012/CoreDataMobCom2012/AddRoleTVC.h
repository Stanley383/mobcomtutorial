//
//  AddRoleTVC.h
//  CoreDataMobCom2012
//
//  Created by Salvador Aguinaga on 8/30/12.
//  Copyright (c) 2012 Salvador Aguinaga. All rights reserved.
//

#import <UIKit/UIKit.h>

@class AddRoleTVC;
@protocol AddRoleTVCDelegate
- (void)theSaveButtonOnTheAddRoleTVCWasTapped:(AddRoleTVC *)controller;
@end

@interface AddRoleTVC : UITableViewController
@property (nonatomic, weak) id <AddRoleTVCDelegate> delegate;

- (IBAction)save:(id)sender;

@end