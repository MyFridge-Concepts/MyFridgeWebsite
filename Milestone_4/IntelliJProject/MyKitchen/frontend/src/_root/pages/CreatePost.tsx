import React from 'react'
import RecipeForm from "@/components/form/RecipeForm.tsx";

const CreatePost = () => {
    return (
        <div className={"flex flex-1"}>
            <div className={"common-container"}>

                <div className={"max-w-5l flex-start gap-3 justify-start"}>
                    <img
                        src={"/assets/icons/add-recipe.svg"}
                        alt={"Create Post"}
                        width={36}
                        height={36}
                        />
                    <h2 className={"h3-bold md:h2-bold text-left w-full "}>Create Recipe</h2>

                </div>
                <RecipeForm/>
            </div>




        </div>
    )
}
export default CreatePost