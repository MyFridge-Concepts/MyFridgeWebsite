import {
useQuery,
useMutation,
useQueryClient,
    useInfiniteQuery
} from '@tanstack/react-query'
import {createRecipe, createUserAccount, signInAccount, signOutAccount} from "@/lib/firebase/api.ts";
import {INewRecipe, INewUser} from "@/types";




// Mutation for creating a new user
export const useCreateUserAccount = () => {
    return useMutation({
        mutationFn: (userData: any) => createUserAccount(userData),
    });
};

// Mutation for signing in a user
export const useSignInAccount = () => {
    return useMutation({
        mutationFn: ({ email, password }: { email: string; password: string }) =>
            signInAccount({ email, password }),
    });
};

export const useSignOutAccount = () => {
    return useMutation({
        mutationFn: signOutAccount,
    });
};

export const useCreateRecipe = () => {
    return useMutation({
        mutationFn: (recipe: INewRecipe) => createRecipe(recipe),

    });
};
