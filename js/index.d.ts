export declare var updateFlowDict: {
  IMMEDIATE: 1;
  FLEXIBLE: 0;
};
export function startUpdateFlow(
  appUpdateType: string,
  clientVersionStalenessDays?: string
): Promise<string>;
export function onCompleteUpdate(): Promise<string>;
export function checkUpdateAvailability(): Promise<string>;
