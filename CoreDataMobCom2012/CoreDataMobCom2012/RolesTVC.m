//
//  RolesTVC.m
//  CoreDataMobCom2012
//
//  Created by Salvador Aguinaga on 8/30/12.
//  Copyright (c) 2012 Salvador Aguinaga. All rights reserved.
//

#import "RolesTVC.h"

@implementation RolesTVC

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Roles Cell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier];
    }
    
    // Configure the cell...
    
    return cell;
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
	if ([segue.identifier isEqualToString:@"Add Role Segue"])
	{
        NSLog(@"Setting RolesTVC as a delegate of AddRolesTVC");
        
        AddRoleTVC *addRoleTVC = segue.destinationViewController;
        addRoleTVC.delegate = self;
	}
}

- (void)theSaveButtonOnTheAddRoleTVCWasTapped:(AddRoleTVC *)controller
{
    // do something here like refreshing the table or whatever
    
    // close the delegated view
    [controller.navigationController popViewControllerAnimated:YES];
}

@end