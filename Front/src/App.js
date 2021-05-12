import PartPickerDrawer from './components/PartPickerDrawer'
import SignIn from './components/SignIn'
import SingUp from './components/SignUp'
import {BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import LogedPartPicker from './components/LogedPartPicker'


function App() {
  return (
    <div className="App">
     <Router>
        <Switch>
          <Route exact path="/login" component={SignIn} />
          <Route exact path="/" component={PartPickerDrawer} />
          <Route exact path="/signup" component={SingUp} />
          <Route exact path="/loged" component={LogedPartPicker} />

        </Switch>
   </Router>

    </div>
  );
}

export default App;
