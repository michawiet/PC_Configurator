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
import OrdersHistory from "./user/OrdersHistory";
import OrderConfirmation from "./user/OrderConfirmation";

const routes = [
	{
		path: '/logowanie',
		component: SignIn,
		private: false,
		accessIfAuth: false,
	},
	{
		path: '/rejestracja',
		component: SignUp,
		private: false,
		accessIfAuth: false,
	},
	{
		path: '/',
		component: HomePage,
		private: false,
		accessIfAuth: true,
		routes: [
			{
				path: '/zamówienie',
				component: OrderConfirmation,
				private: true,
				accessIfAuth: true,
			},
			{
				path: '/zamowienia',
				component: OrdersHistory,
				private: true,
				accessIfAuth: true,
			},
			{
        path: '/koszyk',
        component: Cart,
				private: true,
				accessIfAuth: true,
      },
      {
				path: '/cpu',
				component: CpuPicker,
				private: false,
				accessIfAuth: true,
      },
			{
				path: '/gpu',
				component: GpuPicker,
				private: false,
				accessIfAuth: true,
			},
      {
				path: '/motherboard',
				component: MotherboardPicker,
				private: false,
				accessIfAuth: true,
			},
      {
				path: '/ram',
				component: RamPicker,
				private: false,
				accessIfAuth: true,
			},
      {
				path: '/psu',
				component: PsuPicker,
				private: false,
				accessIfAuth: true,
			},
      {
				path: '/case',
				component: CasePicker,
				private: false,
				accessIfAuth: true,
			},
      {
				path: '/storage',
				component: StoragePicker,
				private: false,
				accessIfAuth: true,
			},
      {
				path: '/cooler',
				component: CoolerPicker,
				private: false,
				accessIfAuth: true,
			},
      {
        path: '/konfigurator',
        component: ConfigurationStartPanel,
				private: false,
				accessIfAuth: true,
      }
		],
	},
];

export default routes;