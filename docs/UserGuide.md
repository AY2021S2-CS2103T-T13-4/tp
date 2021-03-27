---
layout: page
title: User Guide
---

Welcome to the PocketEstate User Guide! Learn how to use PocketEstate to efficiently organize property data, manage clientele information and keep track of your upcoming appointments.

## Table of Contents

1. Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction

### What is PocketEstate?

PocketEstate is a desktop application for property agents efficiently and effectively manage property data and appointment schedules. With PocketEstate, you can easily organize your property and clientele information, as well as keeping track of your schedules and deadlines.

PocketEstate is also highly optimized for users who can type fast and prefer typing over other means of input, allowing fast completion of most tasks solely via Command Line Interface (CLI).

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `pocketestate.jar` from [here](https://github.com/AY2021S2-CS2103T-T13-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your PocketEstate.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list all`** : Lists all properties and appointments in the app.

   * **`add property`**`n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021` : Adds a property with the corresponding information to the PocketEstate app.

   * **`delete property`**`2` : Deletes the 2nd property shown in the current list of properties.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add property n/NAME`, `NAME` is a parameter which can be used as `add property n/Mayfair`.

* Items in square brackets are optional.<br>
  e.g `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS...]` can be used as <br>`add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021` <br> or as <br>`add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021 r/Urgent to sell cn/Alice cc/91234567 ce/alice@gmail.com ca/$800,000 tags/4 bedrooms, No need for renovation`.

* Items with `…` after them can be used multiple times, including zero times.<br>
  e.g. `[OPTION]...` can be used as `  ` (i.e. 0 times), `pl/$1,000,000`, `pl/$1,000,000 t/Condo` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME r/REMARKS`, `r/REMARKS n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/The Mayfair n/Mayfair`, only `n/Mayfair` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a property: `add property`

Adds a property to the app.

Format: `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]​`

Description:
* There can be multiple tags but different tags should be separated with a comma. <br> e.g. `tags/TAGS_SEPARATED_BY_COMMAS` can be used as `tags/Freehold`, `tags/Freehold, 5 bedrooms`, `tags/Freehold, 5 bedrooms, Near MRT` etc.

Examples:
* `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021`
* `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021 r/Urgent to sell cn/Alice cc/91234567 ce/alice@gmail.com ca/$800,000 tags/4 bedrooms, No need for renovation`

### Adding an appointment: `add appointment`

Adds an appointment to the app.

Format: `add appointment n/NAME r/REMARKS d/DATE t/TIME​`

Examples:
* `add appointment n/Meet Alex r/At M Hotel d/17-2-2021 t/1500`

### Listing all properties and appointments : `list all`

Shows a list of all properties and appointments in the app.

### Listing all properties : `list property`

Shows a list of all properties in the app.

### Listing all appointments : `list appointment`

Shows a list of all appointments in the app.

### Sorting : `sort`

Sorts and shows a list of properties or appointments that is sorted according to the comparator provided.

Formats:
* `sort appointment o/<asc or desc> k/<datetime or name>`
* `sort property o/<asc or desc> k/<price or address or postalcode or deadline or name>`

Description:
* Sorts appointment or property by the specified sorting key in ascending or descending order.
* The sorting key and sorting order fields must be specified.

Examples:
*  `sort appointment o/asc k/datetime` Sorts `appointment` by `datetime` in ascending order.
*  `sort property o/desc k/price` Sorts `property` by `price` in descending order.

### Editing a property : `edit property`

Overwrites the information of the property according to the flags provided.

Formats: `edit property INDEX [n/NAME] [t/PROPERTY_TYPE] [a/ADDRESS] [p/POSTAL_CODE] [d/DEADLINE] [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]​`

<div markdown="block" class="alert alert-info">

**:bulb: Reminder:**<br>

* There can be multiple tags but different tags should be separated with a comma. <br> e.g. `tags/TAGS_SEPARATED_BY_COMMAS` can be used as `tags/Freehold`, `tags/Freehold, 5 bedrooms`, `tags/Freehold, 5 bedrooms, Near MRT` etc.

</div>

Description:
* Edits the entry at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit property 1 r/Urgent to sell cn/Alice` Edits the remark and client name of the 1st property to be `Urgent to sell` and `Alice` respectively.

### Editing an appointment : `edit appointment`

Overwrites the information of the appointment according to the flags provided.

Formats: `edit appointment INDEX [n/NAME] [r/REMARKS] [d/DATE] [t/TIME]`

Description:
* Edits the entry at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit appointment 3 d/21-03-2021 r/at M hotel` Edits the date and remark of the 3rd appointment to be `21-03-2021` and `at M hotel` respectively.

### Updating the status of a property : `update`

Updates the status of a property from Option to Purchase, to Sales and Purchase Agreement to Completion

Formats:
* `update INDEX u/new AMOUNT`
* `update INDEX u/[proceed][cancel]`

Description:
* Edits the property at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* The `new` keyword can only be used on a property without an existing status
* `proceed` or `cancel` can only be used on a property with an existing status
* `proceed` would move the status on to the next one. e.g. Option to Sales Agreement or Sales Agreement to Completion
* `cancel` would remove the status of the property
* At least one of the optional fields must be provided.

Examples:
*  `update 1 u/new 600000` Creates a new status with amount 600000 for the 1st property.
*  `update 3 u/proceed` Moves the status of the 3rd property to next one.

### Removing an entry : `delete`

Deletes the specified property or appointment from the app.

Formats:
* `delete appointment INDEX`
* `delete property INDEX`

Description:
* Deletes the appointment or property at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …
* The field INDEX must be provided.

Examples:
*  `delete appointment 7` Deletes the `appointment` at index `7`.
*  `delete property 7` Deletes the `property` at index `7`.

### Searching properties: `find property`

Finds properties that match the criterion provided.

Formats:
* `find property [KEYWORD] [OPTION...]`

Description:
* There can be 0 or more keywords and 0 or more options, but keywords and options cannot be both empty. All text are case insensitive. 

Options:
* `t/[PROPERTY_TYPE]`

    Search for properties whose housing type field contain patterns specified in `[REMARKS]`. 
    
    The following housing types are supported: 
    * hdb
    * condo
    * landed

* `pm/[PRICE]`

    Search for properties with prices more than `[PRICE]`. 

* `pl/[PRICE]`

    Search for properties with prices less than `[PRICE]`. 

Examples:
* `find property jurong west`
* `find property pm/500000`
* `find property bishan north t/hdb pl/$1,000,000`

### Searching appointments: `find appointment`

Finds appointments that match the criterion provided.

Formats:
* `find appointment [KEYWORD]`

Description:
* There can be 0 or more keywords. Keywords are case insensitive. 

Examples:
* `find appointment bob`
* `find appointment sunday`

### Searching clients: `find client`

Finds appointments that matches the keywords and properties whose clients matches the same keywords. Both are done at the same time. 

Formats:
* `find client [KEYWORD]`

Description:
* There can be 0 or more keywords. Keywords are case insensitive. 

### Clearing all entries : `clear`

Clears all properties or appointments or both from the app.

Formats:
* `clear property`
* `clear appointment`
* `clear all`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PocketEstate data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PocketEstate data are saved as a JSON file `[JAR file location]/data/pocketestate.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PocketEstate will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous PocketEstate home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add property** | `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]` <br> e.g., `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021 r/Urgent to sell cn/Alice cc/91234567 ce/alice@gmail.com ca/$800,000 tags/4 bedrooms, No need for renovation`
**Add appointment** | `add appointment n/NAME r/REMARKS d/DATE t/TIME` <br> e.g., `add appointment n/Meet Alex r/At M Hotel d/17-2-2021 t/1500`
**Clear** | `clear property` <br> `clear appointment` <br> `clear all`
**Edit property** | `edit property INDEX [n/NAME] [t/PROPERTY_TYPE] [a/ADDRESS] [p/POSTAL_CODE] [d/DEADLINE] [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE] [tags/TAGS_SEPARATED_BY_COMMAS]`<br> e.g.,`edit property 1 r/Urgent to sell cn/Alice`
**Edit appointment** | `edit appointment INDEX [n/NAME] [r/REMARKS] [d/DATE] [t/TIME]`<br> e.g.,`edit appointment 3 d/2021-03-28 r/at M hotel`
**Add new status** | `update INDEX new AMOUNT`<br> e.g.,`update 1 new 600000`
**Update status** | `update INDEX [proceed][cancel]`<br> e.g. `update 3 proceed`
**Find properties** | `find property [KEYWORD] [OPTION...]` <br> Options: <br> <ul><li>`t/[HOUSING TYPE]`</li><li>`pm/[PRICE LOWER LIMIT]`</li><li>`pl/[PRICE UPPER LIMIT]`</li></ul> e.g., `find property bishan north t/hdb pl/$1,000,000`
**Find appointments** | `find property [KEYWORD]` <br> e.g., `find appointment bob`
**Find clients** | `find client [CLIENT NAME]` <br> e.g., `find client alice`
**List all** | `list all`
**List property** | `list property`
**List appointment** | `list appointment`
**Sort** | `sort appointment o/<asc or desc> k/<datetime or name>`<br> e.g., `sort appointment o/asc k/datetime`<br><br>`sort property o/<asc or desc> k/<price or address or postalcode or deadline or name>`<br> e.g., `sort property o/asc k/price`
**Remove an entry** | `delete appointment INDEX` <br> e.g. `delete appointment 7` <br><br> `delete property INDEX` <br> e.g. `delete property 7`
**Help** | `help`
