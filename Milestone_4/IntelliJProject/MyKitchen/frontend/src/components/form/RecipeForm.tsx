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
import { Checkbox } from "@/components/ui/checkbox"
import {RecipeValidation} from "@/lib/validation";
import {useCreateRecipe} from "@/lib/react-query/queriesAndMutations.ts";

type SliderProps = React.ComponentProps<typeof Slider>

/*const items =
    [/!* getAllIngredients()*!/
    {
        id: "recents",
        label: "Recents",
    },
    {
        id: "home",
        label: "Home",
    },
    {
        id: "applications",
        label: "Applications",
    },
    {
        id: "desktop",
        label: "Desktop",
    },
    {
        id: "downloads",
        label: "Downloads",
    },
    {
        id: "documents",
        label: "Documents",
    },
]*/

type RecipeFormProps = {
    recipe?: {
        id?: string;
        difficulty: number;
        dish: string;
        description: string;
        instructions: string;
        cookTime: number;
        prepTime: number;
        serving: number;
        imageUrl: string;
        tags: string[];

    }
}


const RecipeForm = ({recipe}: RecipeFormProps) => {
    const {mutateAsync: createRecipe, isPending: isLoadingCreate} = useCreateRecipe();


    // 1. Define your form.
    const form = useForm<z.infer<typeof RecipeValidation>>({
        resolver: zodResolver(RecipeValidation),
        defaultValues: {
            difficulty: recipe? recipe?.difficulty : 1,
            dish: recipe? recipe?.dish : "",
            description: recipe? recipe?.description :"",
            instructions: recipe? recipe?.instructions : "",
            cookTime: recipe? recipe?.cookTime : 0,
            prepTime: recipe? recipe?.prepTime : 0,
            serving: recipe? recipe?.serving : 0,
            file: [],
            tags: recipe? recipe?.tags.join(',') : '',

                    },
    })

    // 2. Define a submit handler.
    function onSubmit(values: z.infer<typeof RecipeValidation>) {
        // Do something with the form values.
        // ✅ This will be type-safe and validated.
        console.log(values)
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="flex flex-col gap-9 w-full max-w-5xl">
                <FormField
                    control={form.control}
                    name="dish"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Dish Name</FormLabel>
                            <FormControl>
                                <Input type="text" className="shad-input" {...field} />
                            </FormControl>
                            <FormMessage/>
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
                    name="cookTime"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Cook Time in minutes</FormLabel>
                            <FormControl>
                                <Input type="number" placeholder="Enter the cook time in minutes" className="shad-input" {...field} />
                            </FormControl>
                            <FormMessage/>
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="prepTime"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Prep Time in minutes</FormLabel>
                            <FormControl>
                                <Input type="number" placeholder="Enter the prep time in minutes" className="shad-input" {...field} />
                            </FormControl>
                            <FormMessage/>
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="serving"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Serving Size - serves _ people</FormLabel>
                            <FormControl>
                                <Input type="number" placeholder="Enter the average serving size" className="shad-input" {...field} />
                            </FormControl>
                            <FormMessage/>
                        </FormItem>
                    )}
                />



               {/* <FormField
                    control={form.control}
                    name="ingredients"
                    render={() => (
                        <FormItem>
                            <div className="mb-4">
                                <FormLabel className="text-base">Ingredients</FormLabel>
                                <FormDescription>
                                    Select the ingredients in your recipe (these act as tags).
                                </FormDescription>
                            </div>
                            {items.map((item) => (
                                <FormField
                                    key={item.id}
                                    control={form.control}
                                    name="items"
                                    render={({ field }) => {
                                        return (
                                            <FormItem
                                                key={item.id}
                                                className="flex flex-row items-start space-x-3 space-y-0"
                                            >
                                                <FormControl>
                                                    <Checkbox
                                                        checked={field.value?.includes(item.id)}
                                                        onCheckedChange={(checked) => {
                                                            return checked
                                                                ? field.onChange([...field.value, item.id])
                                                                : field.onChange(
                                                                    field.value?.filter(
                                                                        (value) => value !== item.id
                                                                    )
                                                                )
                                                        }}
                                                    />
                                                </FormControl>
                                                <FormLabel className="font-normal">
                                                    {item.label}
                                                </FormLabel>
                                            </FormItem>
                                        )
                                    }}
                                />
                            ))}
                            <FormMessage />
                        </FormItem>
                    )}
                />*/}

                <FormField
                    control={form.control}
                    name="difficulty"
                    render={({ field:{ value, onChange } }) => (
                        <FormItem>
                            <FormLabel className={"shad-form-label"}>Difficulty - {value}</FormLabel>
                            <FormControl>
                                <Slider
                                    min={1}
                                    max={5}
                                    step={1}
                                    defaultValue={[value]}
                                    onValueChange={onChange}
                                    className={"accent-off-white"}
                                />

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
                            <FormLabel className={"shad-form-label"}>Add Photos or Videos Of Your Dish</FormLabel>
                            <FormControl>
                                <FileUploader
                                fieldChange={field.onChange}
                                mediaUrl={recipe?.imageUrl}
                                />



                            </FormControl>

                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="tags"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Ingredient Tags (seperated by comma ",")</FormLabel>
                            <FormControl>
                                <Input type="number" placeholder="Carrot, Cake, Meat" className="shad-input" {...field} />
                            </FormControl>
                            <FormMessage/>
                        </FormItem>
                    )}
                />
<div className={"flex gap-4 items-center justify-end"}>
    <Button type="submit" className={"shad-button_primary whitespace-nowrap"}>Submit</Button>
    <Button type={"button"} className={"shad-button_dark_4"}>Cancel</Button>
</div>


            </form>
        </Form>
    )
}
export default RecipeForm
