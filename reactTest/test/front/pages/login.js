import { useDispatch  , useSelector } from 'react-redux';  
import { useState     , useEffect }   from 'react'; 
import { useRouter }  from 'next/router';
import { LOG_IN_REQUEST } from '../reducers/user'; 

export default function LoginPage(){ 
    const  dispatch = useDispatch();
    const  router   = useRouter();
    const { me ,isLoading, error }  = useSelector( (state)=> state.user ); 


    const [email, setEmail] = useState('');  
    const [password, setPassword] = useState(''); 

    const onSubmit = (e)=>{
        e.preventDefault();
        if(!email.trim()){
            console.log('이메일이 빈칸입니다.');
            alert('이메일을 입력해주세요');
            return;
        }
        
        if(!password.trim()){
            console.log('비밀번호가 빈칸입니다.');
            alert('비밀번호를 입력해주세요');
            return;
        }

        dispatch({ type: LOG_IN_REQUEST , data:{email, password} });
    };


    useEffect( ()=>{
        if(me) router.push('/users'); 
    } , [me, router]);    
 
    //렌더링
    return ( <div className="container mt-4">
    <h2 className="mb-3">로그인</h2> 
    <form  onSubmit={onSubmit}    className="w-50 mx-auto">

      <div className="mb-3">
        <input type="email" className="form-control"  placeholder="이메일" value={email} 
           onChange={ (e)=> {setEmail(e.target.value); console.log('email>',e.target.value); } }   />
      </div> 

      <div className="mb-3">
        <input type="password" className="form-control" placeholder="비밀번호" value={password} 
           onChange={ (e)=> {setPassword(e.target.value); console.log('password>',e.target.value); } }  />
      </div> 

      <button type="submit" className="btn btn-primary w-100"  disabled={isLoading}>  
            로그인  
      </button>
    </form> 

    {error && <div className="alert alert-danger mt-3"> {error} </div> }
  </div>  );
}