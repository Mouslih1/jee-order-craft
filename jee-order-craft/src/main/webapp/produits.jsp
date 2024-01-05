<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ordercraftnew.Model.Produit" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    List<Produit> produits;
    if(request.getAttribute("produits") != null)
    {
        produits = (List<Produit>) request.getAttribute("produits");
    }else{
        produits = new ArrayList<>();
    }
%>
<html>
<head>
    <title>Gestion des produits</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@ include file="nav.jsp" %>

<div class="py-10">
    <main>
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <!-- Replace with your content -->
            <!-- This example requires Tailwind CSS v2.0+ -->
            <div class="px-4 sm:px-6 lg:px-8">
                <div class="sm:flex sm:items-center">
                    <div class="sm:flex-auto">
                        <h1 class="text-xl font-semibold text-gray-900">Products</h1>
                        <p class="mt-2 text-sm text-gray-700">A list of all the product in your account including their name product, description, prix and quantite.</p>
                    </div>
                    <div class="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
                        <a href="<%=request.getContextPath()%>/produit/new" class="inline-flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:w-auto">Add product</a>
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
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Description</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Prix</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Quantity</th>
                                        <th scope="col" class="relative py-3.5 pl-3 pr-4 sm:pr-6">
                                            <span class="sr-only">Edit</span>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody class="bg-white">
                                    <!-- Odd row -->
                                    <% for(Produit produit : produits){%>
                                    <tr>
                                        <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6"><%=produit.getName()%></td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500"><%=produit.getDescription()%></td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500"><%=produit.getPrix()%></td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500"><%=produit.getQuantite_produit()%></td>
                                        <td class="relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-6">
                                            <a href="<%=request.getContextPath()%>/produit/edit?id=<%=produit.getId()%>" class="text-indigo-600 hover:text-indigo-900">Edit<span class="sr-only">, Lindsay Walton</span></a>
                                            <a href="<%=request.getContextPath()%>/produit/delete?id=<%=produit.getId()%>" class="text-indigo-600 hover:text-indigo-900">Delete<span class="sr-only">, Lindsay Walton</span></a>
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
</html>