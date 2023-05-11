import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  checkAppUpdate(
    updateCode: number,
    clientVersionStalenessDays: number
  ): Promise<string>;
  completeUpdate(): Promise<string>;
  checkUpdateStatus(): Promise<string>;
}

export default TurboModuleRegistry.get<Spec>('AndroidInappUpdates');
