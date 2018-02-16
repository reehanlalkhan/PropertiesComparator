## Synopsis

This project provides a way to compare properties file. The number and name of the properties file is configurable via properties files (input.properties). The property entries that needs to be ignored are also configurable in the same properties file.

## Usage

The executable jar file is also provided with the project. Double-click on PropertiesComparator.jar will open the UI displaying the keys in each of the properties file in columns. Input files are places besides the jar file. If executing by the source code then run the main program in MainRunnerWithUI. At the bottom of the screen is the Filter Text field. The table rows are filtered based on the text in this text box. The search is incremental and so rows are filtered as the text is being typed. Please note that the filtering is **case-sensitive**. If a quick search is need on the fields that are empty, search by SPACE (single SPACE).

## Motivation

The idea here was to validate multiple properties fields. At times in projects there exists multiple properties file for localization. These files contain the same key but the value would be different for different supported languages. In case there are some keys missed in any of the localization file, then finding them would need to execution of the particular scenario where the localized message is displayed and noting that the message is not localized. Instead this tool could be used to quickly identify them in all of the related files and fix them without needing extensive execution strategy. 

## Installation

Clone the project from github with the URL: https://github.com/reehanlalkhan/PropertiesComparator.git. Inside the folder *Executable* the executable jar _PropertiesComparator.jar_ exists. Place all the properties file in the same folder. Open *input.properties* file and list all the properties files under *input_file_list* property (comma separated). In case there are some properties that needs to be ignored add them to the *ignore_list* else leave the values empty. Then double-click on the jar file.

Make sure that Java (1.7 or above) is installed.

## Contributors

If you wish to contribute do contact. Do go ahead and clone as necessary. Don't forget to get credits if it helps you. Do raise any defects found under **Issues** tab of the project.

## License

This project is licensed under Mozilla Public License ("MPL"), an open source/free software license. See https://www.mozilla.org/en-US/MPL/
