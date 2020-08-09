[@bs.module "react-native"]
[@bs.scope ("NativeModules", "AndroidInappUpdates")]
external checkAppUpdate: (int, int) => string = "checkAppUpdate";

let updateFlowDict = Js.Dict.fromList([("IMMEDIATE", 1), ("FLEXIBLE", 0)]);

let startUpdateFlow =
    (~appUpdateType: string, ~clientVersionStalenessDays: int=0): string => {
  let updateCode =
    switch (
      Js.Dict.get(updateFlowDict, String.uppercase_ascii(appUpdateType))
    ) {
    | Some(value) => value
    | None => 1
    };

  checkAppUpdate(updateCode, clientVersionStalenessDays);
};
