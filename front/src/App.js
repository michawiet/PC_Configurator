import { BrowserRouter as Router, Switch } from 'react-router-dom';
import { AuthProvider } from "./AuthContext"
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
