export enum UpdateFlow {
  IMMEDIATE = 1,
  FLEXIBLE = 0,
}
export function startUpdateFlow(
  appUpdateType: UpdateFlow,
  clientVersionStalenessDays?: string
): Promise<string>;
export function onCompleteUpdate(): Promise<string>;
export function checkUpdateAvailability(): Promise<string>;
