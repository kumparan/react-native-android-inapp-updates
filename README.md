# react-native-android-inapp-updates
![npm](https://img.shields.io/npm/v/@gurukumparan/react-native-android-inapp-updates?style=for-the-badge)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=for-the-badge)](http://makeapullrequest.com)

![](https://media.giphy.com/media/04erHM5iNRnKAPusyR/giphy.gif)

React Native implementation of the Android In-App Update API. 
For more information on InApp Updates you can check the official [documentation](https://developer.android.com/guide/app-bundle/in-app-updates). 

Build with ReasonML. 

### Project Milestone

- [x] `startUpdateFlow`  Basic implementation.
- [x] Implement event emitter for flexible in-app updates download progress and downloaded status.
- [x] Implement `clientVersionStalenessDays` check for `startUpdateFlow`.




### Requirements
* In-app updates works only with devices running Android 5.0 (API level 21) or higher.
* In-app updates can only work when signed with the same signing key as your app on the play store.

## Getting started

`$ npm install @gurukumparan/react-native-android-inapp-updates --save`

or

`$ yarn add @gurukumparan/react-native-android-inapp-updates`

### For react-native@0.60.0 or above

As [react-native@0.60.0](https://reactnative.dev/blog/2019/07/03/version-60) or above supports autolinking, so there is no need to run linking process. 
Read more about autolinking [here](https://github.com/react-native-community/cli/blob/master/docs/autolinking.md).

### Mostly automatic installation

`$ react-native link @gurukumparan/react-native-android-inapp-updates`

## Usage
```javascript
import {startUpdateFlow} from '@gurukumparan/react-native-android-inapp-updates';
const updateModes = 'flexible';

try {
    const result = await startUpdateFlow(updateModes);
} catch (e) {
    console.log('error:', e);
}
```
## Reference
### Methods
#### startUpdateFlow()
```javascript
promise string startUpdateFlow(appUpdateType,clientVersionStalenessDays)
```
##### Input
| Input             | Description                   | Type                              | Default Value 
| -------------     | -------------                 | -------------                     | ------------- |
| appUpdateType     | Android In-app updates type   | enum(`flexible` or `immediate`)   | immediate   |
| clientVersionStalenessDays     | If an update is available In-app modal will only triger after `x` number of days since the Google Play Store app on the user's device has learnt about an available update.    | `int`   | 0   |

##### Promise Resolve
| Value                     | Description                            
| -------------             | -------------                          
| `Canceled`                | In-app modal canceled by user
| `Successful`              | User press the update button

##### Promise Reject
| Value                                     | Description                         
| -------------                             | -------------                           
| `checkAppUpdate failure:`                 | `appUpdateInfoTask` failed getting result. This can mean numerous reason check the log for more explanation. 
| `No update available`                    | There is no update available with the `appUpdateType` type.   
| `startUpdateFlow failure:`                | Failed starting the Google Play In-app updates modal.   

#### checkUpdateAvailability()
```javascript
promise string checkUpdateAvailability()
```

##### Promise Resolve
| Value                     | Description                            
| -------------             | -------------                          
| `Update available`        | Application update is available

##### Promise Reject
| Value                                     | Description                         
| -------------                             | -------------                           
| `checkAppUpdate failure:`                 | `appUpdateInfoTask` failed getting result. This can mean numerous reason check the log for more explanation. 
| `No update available`                    | There is no update available

#### onCompleteUpdate()
```javascript
promise string onCompleteUpdate()
```

##### Promise Resolve
| Value                     | Description                            
| -------------             | -------------                          
| `success`        | Application update succeed

##### Promise Reject
| Value                                     | Description                         
| -------------                             | -------------                           
| `Download is not completed`                    | Application update process fail

❤️ From Indonesia
