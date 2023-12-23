<%
    String name = "";
    if(session.getAttribute("name") != null)
    {
        name = session.getAttribute("name").toString();
    }else{
        response.sendRedirect("index.jsp");
    }
%>

<div class="relative bg-white">
    <div class="absolute inset-0 shadow z-30 pointer-events-none" aria-hidden="true"></div>
    <div class="relative z-20">
        <div class="max-w-7xl mx-auto flex justify-between items-center px-4 py-5 sm:px-6 sm:py-4 lg:px-8 md:justify-start md:space-x-10">
            <div>
                <a href="#" class="flex">
                    <span class="sr-only">Workflow</span>
                    <strong>Nav/bar x</strong>
                </a>
            </div>
            <div class="hidden md:flex-1 md:flex md:items-center md:justify-between">
                <nav class="flex space-x-10">
                    <a href="welcome.jsp" class="text-base font-medium text-gray-500 hover:text-gray-900"> Dashboard </a>
                    <a href="<%=request.getContextPath()%>/client" class="text-base font-medium text-gray-500 hover:text-gray-900"> Gestion clients </a>
                    <a href="<%=request.getContextPath()%>/commande" class="text-base font-medium text-gray-500 hover:text-gray-900"> Gestion commandes </a>
                    <a href="<%=request.getContextPath()%>/user" class="text-base font-medium text-gray-500 hover:text-gray-900"> Gestion users </a>
                    <a href="<%=request.getContextPath()%>/produit" class="text-base font-medium text-gray-500 hover:text-gray-900"> Gestion produits </a>
                    <a href="#" class="text-base font-medium text-gray-500 hover:text-gray-900"> Situation commandes </a>
                </nav>
                <div class="flex items-center md:ml-12">
                    <a href="logout" class="ml-8 inline-flex items-center justify-center px-4 py-2 border border-transparent rounded-md shadow-sm text-base font-medium text-white bg-indigo-600 hover:bg-indigo-700"> Logout </a>
                </div>
            </div>
        </div>
    </div>
</div>