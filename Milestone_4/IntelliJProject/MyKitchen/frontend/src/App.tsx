import { Routes, Route } from 'react-router-dom';
import './globals.css';
import  SigninForm  from './_auth/forms/SigninForm';
import {
    AllUsers,
    CreateRecipe,
    EditRecipe,
    Explore,
    Home,
    LikedRecipes,
    RecipeDetails,
    Profile,
    Saved,
    UpdateProfile,
} from './_root/pages';
import SignupForm from './_auth/forms/SignupForm';
import AuthLayout from './_auth/AuthLayout';
import RootLayout from './_root/RootLayout';
import { Toaster } from "@/components/ui/toaster"


const App = () => {
  return (
    <main className="flex h-screen">
      <Routes>
        {/* private shit */}
          <Route element={<AuthLayout />}>
          <Route path="/sign-in" element={<SigninForm />} />
          <Route path="/sign-up" element={<SignupForm />} />
          </Route>

      
        {/* pub shit */}
        <Route element={<RootLayout />}>
        <Route index element={<Home />} />
            <Route path={"/explore"} element={<Explore />} />
            <Route path={"/saved"}   element={<Saved />} />
            <Route path={"/all-users"} element={<AllUsers />} />
            <Route path={"/create-recipe"} element={<CreateRecipe />} />
            <Route path={"/update-recipe/:id"} element={<EditRecipe />} />
            <Route path={"/recipe/:id"} element={<RecipeDetails />} />
            <Route path={"/profile/:id"} element={<Profile />} />
            <Route path={"/update-profile/:id"} element={<UpdateProfile />} />
            {/*<Route path={"/liked-posts"} element={<LikedRecipes />} />*/}



        </Route>

      </Routes>
        <Toaster />
      </main>
  )
}

export default App
