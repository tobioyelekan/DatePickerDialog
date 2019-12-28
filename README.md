# DatePickerDialog
This is an easy to use simple date picker dialog library

## Screenshots

<img src=https://github.com/tobioyelekan/DatePickerDialog/blob/master/screenshots/Screenshot_blue.jpg height="400" width="200"> <img src=https://github.com/tobioyelekan/DatePickerDialog/blob/master/screenshots/Screenshot_black.jpg height="400" width="200">
<img src=https://github.com/tobioyelekan/DatePickerDialog/blob/master/screenshots/Screenshot_toast.jpg height="400" width="200">
## Setup
Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
  maven { url 'https://jitpack.io' }
  }
 }
```

Add the dependency:
```
implementation 'com.github.tobioyelekan:DatePickerDialog:1.0.0'
```

## Usage
Follow the instructions to implement

Pass an existing date to `DatePicker` to show date on display (for cases like if a user already has a date of birth), if not pass `null`

```
val datePicker = DatePicker(null, null, null)
```
or

```
val datePicker = DatePicker(25, 9, 1995)
```

(optional) Set background color for the date text
```
datePicker.setDateBackgroundColor(android.R.color.black)
```

Set listener in activity/fragment to receive date info
```
datePicker.setListener(DatePickerDialog.OnDateSetListener { _, day, month, year ->
val date = (getString(R.string.format_date, year, month.inc(), day))
Toast.makeText(this, date, Toast.LENGTH_LONG).show()
})
```

Lastly, call `show` to display the date picker
```
datePicker.show(supportFragmentManager, "")
```

Oh wait, what about the dialog button color? Gotcha ;) check this SO <a href=https://stackoverflow.com/a/42373688/8900747> answer</a>

Also check the sample app code <a href=https://github.com/tobioyelekan/DatePickerDialog/tree/master/app>here</a>
