import SignIn from "./AuthenticationForms/SignIn";
import SignUp from "./AuthenticationForms/SignUp";
import CpuPicker from "./Tabs/CpuPicker";
import MotherboardPicker from "./Tabs/MotherboardPicker";
import HomePage from "./HomePage";
import RamPicker from "./Tabs/RamPicker";
import PsuPicker from "./Tabs/PsuPicker";
import CasePicker from "./Tabs/CasePicker";
import StoragePicker from "./Tabs/StoragePicker";
import CoolerPicker from "./Tabs/CoolerPicker";
import GpuPicker from "./Tabs/GpuPicker";
import Cart from "./Cart/Cart";
import ConfigurationStartPanel from "./Tabs/ConfigurationStartPanel";

const routes = [
	{
		path: '/logowanie',
		component: SignIn,
	},
	{
		path: '/rejestracja',
		component: SignUp,
	},
	{
		path: '/',
		component: HomePage,
		routes: [
      {
        path: '/koszyk',
        component: Cart,
      },
      {
				path: '/cpu',
				component: CpuPicker,
      },
			{
				path: '/gpu',
				component: GpuPicker,
			},
      {
				path: '/motherboard',
				component: MotherboardPicker,
			},
      {
				path: '/ram',
				component: RamPicker,
			},
      {
				path: '/psu',
				component: PsuPicker,
			},
      {
				path: '/case',
				component: CasePicker,
			},
      {
				path: '/storage',
				component: StoragePicker,
			},
      {
				path: '/cooler',
				component: CoolerPicker,
			},
      {
        path: '/konfigurator',
        component: ConfigurationStartPanel,
      }
		],
	},
];

export default routes;