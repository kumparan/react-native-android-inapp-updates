import { NativeModules } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-android-inapp-update' doesn't seem to be linked. Make sure: \n\n` +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const AndroidInappUpdateModule = isTurboModuleEnabled
  ? require('./NativeAndroidInAppUpdate').default
  : NativeModules.AndroidInappUpdates;

const AndroidInappUpdates = AndroidInappUpdateModule
  ? AndroidInappUpdateModule
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export enum UpdateFlow {
  IMMEDIATE = 1,
  FLEXIBLE = 0,
}

const checkAppUpdate = (
  updateCode: number,
  clientVersionStalenessDays: number
): Promise<string> => {
  return AndroidInappUpdates.checkAppUpdate(
    updateCode,
    clientVersionStalenessDays
  );
};

const completeUpdate = (): Promise<string> => {
  return AndroidInappUpdates.completeUpdate();
};

const checkUpdateStatus = (): Promise<string> => {
  return AndroidInappUpdates.checkUpdateStatus();
};

export const startUpdateFlow = (
  appUpdateType: UpdateFlow,
  clientVersionStalenessDays: number = 0
): Promise<string> => {
  return checkAppUpdate(appUpdateType, clientVersionStalenessDays);
};

export const onCompleteUpdate = (): Promise<string> => {
  return completeUpdate();
};

export const checkUpdateAvailability = (): Promise<string> => {
  return checkUpdateStatus();
};
