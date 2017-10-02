This is a project for Assignment 1 for CMPUT 301 Fall term 2017 at University of Alberta.

The whole assignment was writen by shipin1 alone without any other colaborator.

Description of the app: 
					The app was created as a counter manager. It can create/edit/delete/reset any counter as the user's wish.

					The inputed data will be store localy and would not lose if close the app or restart the device.


Class Description:
		1. counter: Making the Arraylist object "Counters". Used to store counter related information such as name, comment, values and dates.The class was used by "MainActivity" class.

		2. DisplayCounterAvtivity: Handling the functionality of the "Detailed" page of each counter. Used to modify/save/delete the existing counter. class activate when counter was clicked on the main page. information passed by using "intnet,putExtra()"

		3.MainActivity: App start up page Display listView of Counters Call other class such as DisplayCounterActivity and NewCounterActivity information passed by using "intnet,putExtra()"

		4.NewCounterActivity: functionality of creating new counters. Asking user input data such as: counter name, counter initial value, counter comments. Set the current value of counter equal to initial value by default Auto generate date when creation was completed.



Reference:
		https://stackoverflow.com/
		https://www.tutorialspoint.com/java/
		https://developer.android.com/training/index.html