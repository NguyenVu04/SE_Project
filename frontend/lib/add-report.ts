import { spssAxios } from "./axios-config";

export default async function addReport(from: string, to: string) {
    const response = await spssAxios.post('/report', {
        from: from,
        to: to
    }, {
        validateStatus: () => true
    });

    return response.status;
}