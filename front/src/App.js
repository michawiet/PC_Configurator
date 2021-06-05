import UnauthenticatedPartPicker from './components/UnauthenticatedPartPicker';
import SignIn from './components/AuthenticationForms/SignIn';
import SingUp from './components/AuthenticationForms/SignUp';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AuthenticatedPartPicker from './components/AuthenticatedPartPicker';
import { AuthProvider } from "./AuthContext"
import PrivateRoute from "./PrivateRoute"
import Basket from './components/Cart/Cart'
import routes from './components/Routes';
import RouteWithSubRoutes from './components/RouteWithSubRoutes';

function App() {
  return (
    <div>

      <Router>
        <AuthProvider>
        <Switch>
          {routes.map((route, i) => (
            <RouteWithSubRoutes key={i} {...route} />
          ))}
        </Switch>
        </AuthProvider>
      </Router>
    </div>
  );
}

export default App;
