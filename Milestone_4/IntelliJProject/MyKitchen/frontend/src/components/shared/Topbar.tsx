import {Link} from "react-router-dom";
import {Button} from "@/components/ui/button";
import {useSignOutAccount} from "@/lib/react-query/queriesAndMutations.ts";

const Topbar = () => {
    const {mutate: signOut, isSuccess} = useSignOutAccount();


    return (
        <section className="topbar">

            <div className={"flex-between py-4 px-5"}>

                <Link to={"/"} className={"flex gap-3 items-center"}>
                    <img src="/assets/images/logo.svg" alt="logo"
                         width={130} height={325}/>
                </Link>

                <div className={"flex gap-5"}>
                    <Button variant={"ghost"} className={"shad-button_ghost"} onClick={() => signOut()}>
                    <img src="/assets/images/icons/logout.svg" alt="logout"/>
                    </Button>

                </div>

            </div>

        </section>
    )
}
export default Topbar
