import UnauthenticatedPartPicker from './components/UnauthenticatedPartPicker';
import SignIn from './components/SignIn';
import SingUp from './components/SignUp';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AuthenticatedPartPicker from './components/AuthenticatedPartPicker';


function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/" component={UnauthenticatedPartPicker} />
          <Route exact path="/login" component={SignIn} />
          <Route exact path="/signup" component={SingUp} />
          <Route exact path="/loged" component={AuthenticatedPartPicker} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
