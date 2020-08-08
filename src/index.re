[@bs.module "react-native"]
[@bs.scope ("NativeModules", "AndroidInappUpdates")]
external checkAppUpdate: int => _ = "checkAppUpdate";

let updateFlowDict = Js.Dict.fromList([("IMMEDIATE", 1), ("FLEXIBLE", 0)]);

type appUpdateType =
  | IMMEDIATE
  | FLEXIBLE;

let startUpdateFlow = (appUpdateType): appUpdateType => {
  let updateCode =
    switch (
      Js.Dict.get(updateFlowDict, String.uppercase_ascii(appUpdateType))
    ) {
    | Some(value) => value
    | None => 1
    };
  checkAppUpdate(updateCode);
};
