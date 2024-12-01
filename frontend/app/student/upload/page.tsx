'use server';

import DocumentUpload from '@/components/DocumentUpload'
import getUserId from '@/lib/user-id';

const PrintPage = async () => {
  const studentId = await getUserId("student");
  return (
    <div
      className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
        <DocumentUpload studentId={studentId} />
    </div>
  )
}

export default PrintPage;