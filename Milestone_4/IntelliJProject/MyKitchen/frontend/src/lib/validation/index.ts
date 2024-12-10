import {z} from "zod"

export const SignupValidation = z.object({
    first_name: z.string().min(2,{message: 'Too short'}),
    last_name: z.string().min(2, {message: 'Too short'}),
    username: z.string().min(2, {message:'Too short'}).max(50, {message:'Too long'}),
    email: z.string().email(),
    password: z.string().min(6, {message: 'Too short'}),
})

export const SigninValidation = z.object({
    email: z.string().email(),
    password: z.string().min(6, {message: 'Too short'}),
})
