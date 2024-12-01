import { spssAxios } from "./axios-config";

export default async function getUserInfo(
    id: string,
    role: "student" | "spso" | "admin") {

    // switch (role) {
    //     case "student":
    //         const student = await spssAxios.get('/')
    //     case "spso":
    //         return await getSpso(id);
    //     case "admin":
    //         return await getAdmin(id);
    // }
}