import UnauthenticatedPartPicker from './components/UnauthenticatedPartPicker';
import SignIn from './components/AuthenticationForms/SignIn';
import SingUp from './components/AuthenticationForms/SignUp';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AuthenticatedPartPicker from './components/AuthenticatedPartPicker';
import { AuthProvider } from "./AuthContext"
import PrivateRoute from "./PrivateRoute"


function App() {
  return (
    <div>
      <Router>
        <AuthProvider>
        <Switch>
          <Route exact path="/" component={UnauthenticatedPartPicker} />
          <Route exact path="/login" component={SignIn} />
          <Route exact path="/signup" component={SingUp} />
          <PrivateRoute exact path="/loged" component={AuthenticatedPartPicker} />
        </Switch>
        </AuthProvider>
      </Router>
    </div>
  );
}

export default App;
