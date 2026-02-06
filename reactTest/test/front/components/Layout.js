import  Link from  'next/link';  

export default  function Layout( {  children  } ){ 
    return (
        <div>
            <div className="p-5 bg-primary text-white text-center">
                <h1>공용 레이아웃 헤더</h1> 
            </div>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
            <div className="container-fluid">
                <ul className="navbar-nav">
                <li className="nav-item">
                    <Link href="/users">
                        <a className="nav-link active" href="#">HOME</a>
                    </Link>    
                </li>
                <li className="nav-item">
                    <Link href="/login">
                        <a className="nav-link" href="#">로그인</a>
                    </Link>
                </li>
                <li className="nav-item">
                    <Link href="/join">
                        <a className="nav-link" href="#">회원가입</a>
                    </Link>    
                </li> 
                </ul>
            </div>
            </nav>            
            <main className="container mt-4">{ children }</main>
            <footer className="mt-5 p-4 bg-dark text-white text-center">
                <p>Footer</p>
            </footer>
        </div>
    );
}