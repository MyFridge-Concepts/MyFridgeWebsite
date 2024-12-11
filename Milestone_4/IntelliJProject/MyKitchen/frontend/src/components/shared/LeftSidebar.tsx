
import {Link, useNavigate} from "react-router-dom";
import {Button} from "@/components/ui/button";
import {useSignOutAccount} from "@/lib/react-query/queriesAndMutations.ts";
import {useEffect} from "react";
import {useUserContext} from "@/context/AuthContext.tsx";
import {getCurrentUser} from "@/lib/firebase/api.ts";


const LeftSidebar = () => {
    const {mutate: signOut, isSuccess} = useSignOutAccount();
    const navigate = useNavigate();
    const {user} = useUserContext()


    useEffect(() => {
        if(isSuccess) navigate(0);
    }, [isSuccess])

    console.log("User object:", user);

    return (


        <nav className="left-sidebar">
        <div className={"flex flex-col gap-11"}>

            <Link to={"/"} className={"flex gap-3 items-center"}>
                <img src="/assets/images/logo.svg" alt="logo"
                     width={170} height={36}/>
            </Link>


            <Link to={`/profile/${user.id}`} className={"flex gap-3 items-center"}>
<img src={user.pfp || "/assets/images/profile-placeholder.svg"}
     alt={"profile"}
     className={"h-8 w-8 rounded-full"}/>
<div className={"flex flex-col"}>
    <p className={"body-bold "}>
        {user.first_name} {user.last_name}
    </p>

    <p className={"small-regular text-light-3"}>
        @${user.username}


    </p>
</div>
            </Link>




        </div>

        </nav>


    )
}
export default LeftSidebar
