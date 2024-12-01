'use server';
import MainHeader from "@/components/MainHeader";
import getUserInfo from "@/lib/get-user-info";
import getUserId from "@/lib/user-id";

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const userId = await getUserId("student");
  const userInfo = await getUserInfo(userId, "student");
  return (
    <div className="relative bg-gray-100 w-screen min-h-screen flex flex-col">
      <MainHeader balance={userInfo.balance}/>
      {children}
    </div>
  );
}
