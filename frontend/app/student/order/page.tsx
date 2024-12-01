'use server';
import Order from '@/components/Order'
import getUserId from '@/lib/user-id';

const Page = async () => {
    const studentId = await getUserId("student");
    return (
        <div
            className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
            <Order studentId={studentId}></Order>
        </div>
    )
}

export default Page