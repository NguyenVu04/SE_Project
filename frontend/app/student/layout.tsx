import MainHeader from "@/components/MainHeader";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="relative bg-gray-100 w-screen min-h-screen flex flex-col">
      <MainHeader />
      {children}
    </div>
  );
}
