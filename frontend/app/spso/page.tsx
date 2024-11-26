'use server';
import getUserId from "@/lib/user-id";

export default async function SPSOPage() {
    const id = await getUserId("spso");
    return (
        <div>
            {id}
        </div>
    );
}