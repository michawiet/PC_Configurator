import { useEffect } from "react";
import { Route, useHistory } from 'react-router-dom';
import { useAuth } from "../AuthContext";

const RouteWithSubRoutes = (route) => {
	const { currentUser } = useAuth();
	let history = useHistory();

	useEffect(() => {
		if(route.private === true) {
			if(!currentUser) {
				history.replace("/logowanie");
			}
		} else if(route.accessIfAuth === false) {
			if(currentUser) {
				history.replace("/");
			}
		}
	}, [])

	return (
		<Route
			path={route.path}
			render={(props) => (
				<route.component {...props} routes={route.routes} />
			)}
		/>
	);
};

export default RouteWithSubRoutes;
