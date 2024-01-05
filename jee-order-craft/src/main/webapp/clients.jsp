<%@ page import="java.util.List" %>
<%@ page import="com.example.ordercraftnew.Model.Client" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    List<Client> clients;
if(request.getAttribute("clients") != null)
{
    clients = (List<Client>) request.getAttribute("clients");
}else{
    clients = new ArrayList<>();
}
%>
<html>
<head>
    <title>Gestion des clients</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<div class="min-h-full">
<%@ include file="nav.jsp" %>

<div class="py-10">
    <main>
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <!-- Replace with your content -->
            <!-- This example requires Tailwind CSS v2.0+ -->
            <div class="px-4 sm:px-6 lg:px-8">
                <div class="sm:flex sm:items-center">
                    <div class="sm:flex-auto">
                        <h1 class="text-xl font-semibold text-gray-900">Clients</h1>
                        <p class="mt-2 text-sm text-gray-700">A list of all the client in your account including their name, email and ville.</p>
                    </div>
                    <div class="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
                        <a href="<%=request.getContextPath()%>/client/new" class="inline-flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:w-auto">Add client</a>
                    </div>
                </div>
                <div class="mt-8 flex flex-col">
                    <div class="-my-2 -mx-4 overflow-x-auto sm:-mx-6 lg:-mx-8">
                        <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                            <div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                                <table class="min-w-full divide-y divide-gray-300">
                                    <thead class="bg-gray-50">
                                    <tr>
                                        <th scope="col" class="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6">Name</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Email</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Ville</th>
                                        <th scope="col" class="relative py-3.5 pl-3 pr-4 sm:pr-6">
                                            <span class="sr-only">Edit</span>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody class="bg-white">
                                    <!-- Odd row -->
                                    <% for(Client client : clients){%>
                                    <tr>
                                        <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6"><%=client.getName()%></td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500"><%=client.getEmail()%></td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500"><%=client.getVille()%></td>
                                        <td class="relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-6">
                                            <a href="<%=request.getContextPath()%>/client/edit?id=<%=client.getId()%>" class="text-indigo-600 hover:text-indigo-900">Edit<span class="sr-only">, Lindsay Walton</span></a>
                                            <a href="<%=request.getContextPath()%>/client/delete?id=<%=client.getId()%>" class="text-indigo-600 hover:text-indigo-900">Delete<span class="sr-only">, Lindsay Walton</span></a>
                                        </td>
                                    </tr>
                                    <%}%>
                                    <!-- More people... -->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /End replace -->
        </div>
    </main>
</div>

</div>
</html>