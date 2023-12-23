<%@ page import="java.util.List" %>
<%@ page import="com.example.ordercraftnew.Model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<CommandeProduit> commandeProduit= null;
    if(request.getAttribute("commande_produits") != null){
        commandeProduit = (List<CommandeProduit>) request.getAttribute("commande_produits");
    }

    Commande commande = null;
    if(request.getAttribute("commande") != null)
    {
        commande = (Commande) request.getAttribute("commande");
    }

    System.out.println(commande);
    List<Client> clients = null;
    if(request.getAttribute("clients") != null)
    {
        clients = (List<Client>) request.getAttribute("clients");
    }

    List<Commande> commandes = null;
    if(request.getAttribute("commandes") != null)
    {
        commandes = (List<Commande>) request.getAttribute("commandes");
    }

    List<Produit> produits = null;
    if(request.getAttribute("produits") != null)
    {
        produits = (List<Produit>) request.getAttribute("produits");
    }

    List<Etat> etats = null;
    if(request.getAttribute("etats") != null)
    {
        etats = (List<Etat>) request.getAttribute("etats");
    }

    assert clients != null;
    assert produits != null;
    assert commandes != null;
    assert etats != null;
%>

<html>
<head>
    <title>Gestion des commandes</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<div class="min-h-full">
    <%@ include file="nav.jsp" %>

    <div class="py-10">
        <main>
            <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
                <!-- Replace with your content -->
                <div class="lg:grid lg:grid-cols-12 lg:gap-x-5">
                    <div class="space-y-6 sm:px-6 lg:px-0 lg:col-span-6">
                            <form action="<%=request.getContextPath()%>/commande/add" method="post">
                                <div class="shadow sm:rounded-md sm:overflow-hidden">
                                    <div class="bg-white py-6 px-4 space-y-6 sm:p-6">
                                        <div>
                                            <h3 class="text-lg leading-6 font-medium text-gray-900">commande Information</h3>
                                            <p class="mt-1 text-sm text-gray-500">Use a permanent address where you can receive products.</p>
                                        </div>

                                        <div class="grid grid-cols-6 gap-6">
                                            <div class="col-span-6 sm:col-span-3">
                                                <label for="id_client" class="block text-sm font-medium text-gray-700">Client name</label>
                                                <select id="id_client" name="id_client" autocomplete="id_client" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">

                                                    <option>-- Client name --</option>
                                                    <% for(Client client : clients){ %>
                                                    <option value="<%= client.getId() %>"><%= client.getName()%></option>
                                                    <%}%>
                                                </select>
                                            </div>

                                            <div class="col-span-6 sm:col-span-4">
                                                <label for="address_livraison" class="block text-sm font-medium text-gray-700">Address livraison</label>
                                                <div class="mt-1">
                                                    <textarea id="address_livraison" name="address_livraison"  rows="3" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border border-gray-300 rounded-md" placeholder="description"></textarea>
                                                </div>
                                            </div>

                                            <div class="col-span-6">
                                                <label for="etat_commande" class="block text-sm font-medium text-gray-700">Etat commande</label>
                                                <div class="mt-1 sm:mt-0 sm:col-span-2">
                                                    <select id="etat_commande" name="etat_commande" autocomplete="etat_commande" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                                                        <option>-- Commande état --</option>
                                                        <% for(Etat etat : etats){ %>
                                                        <option value="<%=etat.name()%>"><%= etat.name() %></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
                                        <button type="submit" class="bg-indigo-600 border border-transparent rounded-md shadow-sm py-2 px-4 inline-flex justify-center text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Save</button>
                                    </div>
                                </div>
                            </form>
                    </div>

                    <div class="space-y-6 sm:px-6 lg:px-0 lg:col-span-6">
                        <form action="<%=request.getContextPath()%>/commande/add_info" method="post">
                            <div class="shadow sm:rounded-md sm:overflow-hidden">
                                <div class="bg-white py-6 px-4 space-y-6 sm:p-6">
                                    <div>
                                        <h3 class="text-lg leading-6 font-medium text-gray-900">product Information</h3>
                                        <p class="mt-1 text-sm text-gray-500">Use a permanent quantite where you can get count of product wanted.</p>
                                    </div>

                                    <div class="grid grid-cols-6 gap-6">
                                        <% if(commande != null){ %>
                                        <input type="hidden" name="id_commande" value="<%= commande.getId()%>" />
                                        <%}%>
                                        <div class="col-span-6 sm:col-span-3">
                                            <label for="id_product" class="block text-sm font-medium text-gray-700">Product name</label>
                                            <div class="mt-1 sm:mt-0 sm:col-span-2">
                                                <select id="id_product" name="id_product" autocomplete="id_product" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                                                    <option>-- Product name --</option>
                                                    <% for(Produit produit : produits){ %>
                                                    <option value="<%= produit.getId() %>"><%= produit.getName()%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>

                                       <%-- <div class="col-span-6 sm:col-span-4">
                                            <label for="quantite_commander" class="block text-sm font-medium text-gray-700">Quantity product</label>
                                            <input type="number" name="quantite_commander" min="0" placeholder="quantite commander" value="" id="quantite_commander" autocomplete="quantite_commander" class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                                        </div>
--%>
                                    </div>
                                </div>
                                <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
                                    <button type="submit" class="bg-indigo-600 border border-transparent rounded-md shadow-sm py-2 px-4 inline-flex justify-center text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>


                <% if(commandeProduit != null){%>
                <div class="sm:flex-auto">
                    <h1 class="text-xl font-semibold text-gray-900">Commande no complete</h1>
                    <p class="mt-2 text-sm text-gray-700">You should be added quantity of products go to situation commandes for complete your action.</p>
                </div>
                <div class="mt-8 flex flex-col">
                    <div class="-my-2 -mx-4 overflow-x-auto sm:-mx-6 lg:-mx-8">
                        <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                            <div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                                <table class="min-w-full divide-y divide-gray-300">
                                    <thead class="bg-gray-50">
                                    <tr>
                                        <th scope="col" class="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6">Commande id</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Product name</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Address livraison</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Status</th>
                                        <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Date creation</th>
                                        <th scope="col" class="relative py-3.5 pl-3 pr-4 sm:pr-6">
                                            <span class="sr-only">Edit</span>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody class="divide-y divide-gray-200 bg-white">
                                    <tr>
                                        <%
                                            for (CommandeProduit cp : commandeProduit){
                                                for (Commande c : commandes){
                                                    if(cp.getCommande().getId() == c.getId()){
                                        %>
                                        <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm sm:pl-6">
                                            <div class="flex items-center">
                                                <div class="ml-4">
                                                    <div class="font-medium text-gray-900"><%=cp.getCommande().getId()%></div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                            <div class="text-gray-900"><%=cp.getProduit().getName()%></div>
                                        </td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                            <div class="text-gray-900"><%=c.getAddress_livraison()%></div>
                                        </td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                            <% if(c.getEtat_commande() == Etat.TERMINEE){ %>
                                            <span class="inline-flex rounded-full bg-green-100 px-2 text-xs font-semibold leading-5 text-green-800"><%=c.getEtat_commande()%></span>
                                            <%} else if (c.getEtat_commande() == Etat.EN_COURS) {%>
                                            <span class="inline-flex rounded-full bg-yellow-100 px-2 text-xs font-semibold leading-5 text-yellow-800"><%=c.getEtat_commande()%></span>
                                            <% }else { %>
                                            <span class="inline-flex rounded-full bg-red-100 px-2 text-xs font-semibold leading-5 text-red-800"><%=c.getEtat_commande()%></span>
                                            <%}%>
                                        </td>
                                        <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500"><%=c.getDate()%></td>
                                        <td class="relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-6">
                                            <a href="#" class="text-indigo-600 hover:text-indigo-900">Add quantity</a>
                                        </td>
                                    </tr>

                                    <%
                                                    }
                                                }
                                            }
                                    %>
                                    <!-- More people... -->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /End replace -->
                </div>

            <%}%>
        </main>
    </div>

</html>