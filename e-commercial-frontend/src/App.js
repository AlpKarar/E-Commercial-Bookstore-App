import { BrowserRouter } from "react-router-dom";
import './App.css';
import ContextProvider from "./ContextAPI/ContextProvider";
import BookAppRoutes from "./Routes/Routes";


function App() {

  return (
    <ContextProvider>
      <BrowserRouter>
        <BookAppRoutes/>
      </BrowserRouter>
    </ContextProvider>
  );
}

export default App;