'use server';

import ConfigDoc from '@/components/ConfigDoc'
import getUserId from '@/lib/user-id';
export type OrderInfo = {
  filename: string
  paperSize: string
  pageNumbers: number[],
  singleSided: boolean
  numberOfCopies: number
}

const Page = async () => {
  const studentId = await getUserId("student");
  return (
    <div
      className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
        <ConfigDoc studentId={studentId}/>
    </div>
  )
}

export default Page