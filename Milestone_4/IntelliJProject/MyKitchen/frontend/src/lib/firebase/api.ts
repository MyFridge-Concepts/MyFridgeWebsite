import { createUserWithEmailAndPassword, UserCredential, updateProfile } from "firebase/auth";
import { INewUser } from "@/types";
import {auth} from "@/lib/firebase/config.ts";

// Function to create a new user account with Firebase Authentication
export async function createUserAccount(user: INewUser): Promise<UserCredential | Error> {
     // Initialize Firebase Authentication
    try {
        // Create the user with email and password
        const userCredential = await createUserWithEmailAndPassword(auth, user.email, user.password);
        await updateProfile(userCredential.user, {
            displayName: user.name

        });

        // Update the new user's username and add it to firebase

        // Return the created user credential
        return userCredential;
    } catch (error: unknown) { // Use `unknown` instead of `any`
        // Type guard to check if the error is an instance of Error
        if (error instanceof Error) {
            console.error("Error creating user:", error.message);
            return new Error(error.message);
        } else {
            console.error("An unknown error occurred:", error);
            return new Error("An unknown error occurred");
        }
    }
}
