import { Slider } from "@/components/ui/slider"

import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { z } from "zod"

import { Button } from "@/components/ui/button"
import {
    Form,
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import {Textarea} from "@/components/ui/textarea.tsx";
import FileUploader from "@/components/shared/FileUploader.tsx";



const formSchema = z.object({
    username: z.string().min(2, {
        message: "Username must be at least 2 characters.",
    }),
})

const RecipeForm = () => {
    // 1. Define your form.
    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            username: "",
        },
    })

    // 2. Define a submit handler.
    function onSubmit(values: z.infer<typeof formSchema>) {
        // Do something with the form values.
        // ✅ This will be type-safe and validated.
        console.log(values)
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="flex flex-col gap-9 w-full max-w-5xl">
                <FormField
                    control={form.control}
                    name="description"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel className={"shad-form-label"}>Recipe Description</FormLabel>
                            <FormControl>
                                <Textarea placeholder="Put a recipe Description" {...field} className={"shad-textarea custom-scrollbar"} />
                            </FormControl>

                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="description"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel className={"shad-form-label"}>Recipe Description</FormLabel>
                            <FormControl>
                                <Textarea placeholder="Put a recipe Description" {...field} className={"shad-textarea custom-scrollbar"} />
                            </FormControl>

                            <FormMessage />
                        </FormItem>
                    )}
                />



                <FormField
                    control={form.control}
                    name="instructions"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel className={"shad-form-label"}>Recipe Instructions</FormLabel>
                            <FormControl>
                                <Textarea placeholder="Write the recipe Instructions in here" {...field} className={"shad-textarea custom-scrollbar"} />
                            </FormControl>

                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="difficulty"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel className={"shad-form-label"}>Difficulty</FormLabel>
                            <FormControl>
                                <Slider defaultValue={[1]} max={100} step={1} />

                            </FormControl>

                            <FormMessage />
                        </FormItem>
                    )}
                />



                <FormField
                    control={form.control}
                    name="file"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel className={"shad-form-label"}>Add Photos Of Your Dish</FormLabel>
                            <FormControl>
                                <FileUploader />



                            </FormControl>

                            <FormMessage />
                        </FormItem>
                    )}
                />


                <Button type="submit">Submit</Button>
            </form>
        </Form>
    )
}
export default RecipeForm
