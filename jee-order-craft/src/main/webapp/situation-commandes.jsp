<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ordercraftnew.Model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
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

    List<CommandeProduit> commandeProduits = null;
    if(request.getAttribute("commandeProduits") != null)
    {
        commandeProduits = (List<CommandeProduit>) request.getAttribute("commandeProduits");
    }

    assert commandes != null;
    assert produits != null;
    assert commandeProduits != null;

%>
<html>
<head>
    <title>Gestion des users</title>
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
                            <h1 class="text-xl font-semibold text-gray-900">Commandes</h1>
                            <p class="mt-2 text-sm text-gray-700">A list of all the commandes in your account including their information add quantity of commande or edit état with her situation.</p>
                        </div>
                    </div>
                    <div class="mt-8 flex flex-col">
                        <div class="-my-2 -mx-4 overflow-x-auto sm:-mx-6 lg:-mx-8">
                            <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                                <div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                                    <table class="min-w-full divide-y divide-gray-300">
                                        <thead class="bg-gray-50">
                                        <tr>
                                            <th scope="col" class="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6">Commande id</th>
                                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Client name</th>
                                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Address livrison</th>
                                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Quantity commander</th>
                                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Produit prix</th>
                                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Status</th>
                                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Prix total</th>
                                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Date création</th>
                                            <th scope="col" class="relative py-3.5 pl-3 pr-4 sm:pr-6">
                                                <span class="sr-only">Edit</span>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody class="divide-y divide-gray-200 bg-white">
                                        <%
                                            System.out.println(commandes);
                                        for (Commande commande : commandes){
                                            for (CommandeProduit commandeProduit : commandeProduits){
                                                for(Produit produit : produits) {
                                                    if(commande.getId() == commandeProduit.getCommande().getId() && commandeProduit.getProduit().getId() == produit.getId()){%>

                                        <tr>
                                            <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm sm:pl-6">
                                                <div class="flex items-center">
                                                    <div class="ml-4">
                                                        <div class="font-medium text-gray-900"><%=commande.getId()%></div>
                                                    </div>
                                                </div>
                                            </td>

                                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                                <div class="text-gray-900"><%=produit.getName()%></div>
                                            </td>

                                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                                <div class="text-gray-900"><%=commande.getAddress_livraison()%></div>
                                            </td>

                                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                                <div class="text-gray-900"><%=commandeProduit.getQuantite()%></div>
                                            </td>

                                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                                <div class="text-gray-900"><%=produit.getPrix()%></div>
                                            </td>

                                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                                <% if(commande.getEtat_commande() == Etat.TERMINEE){ %>
                                                <span class="inline-flex rounded-full bg-green-100 px-2 text-xs font-semibold leading-5 text-green-800"><%=commande.getEtat_commande()%></span>
                                                <%} else if (commande.getEtat_commande() == Etat.EN_COURS) {%>
                                                <span class="inline-flex rounded-full bg-yellow-100 px-2 text-xs font-semibold leading-5 text-yellow-800"><%=commande.getEtat_commande()%></span>
                                                <% }else { %>
                                                <span class="inline-flex rounded-full bg-red-100 px-2 text-xs font-semibold leading-5 text-red-800"><%=commande.getEtat_commande()%></span>
                                                <%}%>
                                            </td>

                                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                                <div class="text-gray-900"><%=commandeProduit.getPrix_total()%></div>
                                            </td>

                                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                                <div class="text-gray-900"><%=commande.getDate()%></div>
                                            </td>

                                            <td class="relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-6">
                                                <a href="<%=request.getContextPath()%>/commande-situation/new-quantity?id_produit=<%=produit.getId()%>&id_cp=<%=commandeProduit.getId()%>" class="text-indigo-600 hover:text-indigo-900">Add quantity<span class="sr-only">, Lindsay Walton</span></a>
                                                <a href="<%=request.getContextPath()%>/commande-situation/edit-status?id_commande=<%=commande.getId()%>" class="text-indigo-600 hover:text-indigo-900">Edit status<span class="sr-only">, Lindsay Walton</span></a>
                                            </td>
                                        </tr>
                                        <%            }
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
                    </div>
                </div>
                <!-- /End replace -->
            </div>
        </main>
    </div>

</div>
</html>