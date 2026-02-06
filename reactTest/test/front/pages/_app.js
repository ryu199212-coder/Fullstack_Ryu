import 'bootstrap/dist/css/bootstrap.min.css';  
import Layout  from '../components/Layout';   
import '../styles/globals.css';           
import { wrapper }  from '../store/configureStore';

function MyApp( { Component   , pageProps  }){  
    return (
        <Layout>
            <Component {...pageProps}/>
        </Layout>
    );
} 

export default  wrapper.withRedux( MyApp );
