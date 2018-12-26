package br.com.desafio.run;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

import br.com.desafio.model.Classificacao;
import br.com.desafio.model.Corrida;
import br.com.desafio.model.Piloto;

public class EventoCorrida {
	
	private static List<Corrida> listaVoltas = new ArrayList<>();
	
	private static List<Classificacao> listaPodio = new ArrayList<>();
	
	private static List<Corrida> listaCorridaPrincipal = new ArrayList<>();
	
	public static void main(String[] args) {
		int ordem = 1;
		listaCorridaPrincipal = lerArquivo();
		int auxPosicao = 0;
		LocalTime melhorVolta = LocalTime.parse("00:0" + listaCorridaPrincipal.get(0).getTmpVolta());
		LocalTime tmpPrimeiro = null;
		
		//Loop para pegar a última volta de cada piloto
		for (int count = 0; count < listaCorridaPrincipal.size(); count++) {
			LocalTime tmpVolta = LocalTime.parse("00:0" + listaCorridaPrincipal.get(count).getTmpVolta());
			if(melhorVolta.isAfter(tmpVolta)) {
				melhorVolta = tmpVolta;
			}
			
			for(int x = 0; x < listaPodio.size(); x++) {
				if(listaPodio.get(x).getPiloto().getCodPiloto() == listaCorridaPrincipal.get(count).getPiloto().getCodPiloto()) {
					if(listaPodio.get(x).getPiloto().getMelhorVolta() == null) {
						listaPodio.get(x).getPiloto().setMelhorVolta(LocalTime.parse("00:0" + listaCorridaPrincipal.get(count).getTmpVolta()));
					}
					LocalTime tmpVoltaAtual = LocalTime.parse("00:0" + listaCorridaPrincipal.get(count).getTmpVolta());
					listaPodio.get(x).setCorrida(listaCorridaPrincipal.get(count));
					if(listaPodio.get(x).getPiloto().getMelhorVolta().isAfter(tmpVoltaAtual)) {
						listaPodio.get(x).getPiloto().setMelhorVolta(tmpVoltaAtual);
					}
					
					double media = listaCorridaPrincipal.get(count).getVlMediaVolta() + listaPodio.get(x).getPiloto().getVelocidadeMedia();
					listaPodio.get(x).getPiloto().setVelocidadeMedia(media);
				}
			}
		}
		
		for(int i = 1; i < listaPodio.size(); i++) {
			Classificacao comparaVoltaAnterior = listaPodio.get(i - 1); 
			if(listaPodio.get(i).getCorrida().getNumVolta() >= comparaVoltaAnterior.getCorrida().getNumVolta()
					&& listaPodio.get(i).getCorrida().getHrCorrida().isBefore(comparaVoltaAnterior.getCorrida().getHrCorrida())
					&& listaPodio.get(i).getPosicao() > comparaVoltaAnterior.getPosicao()) {
				auxPosicao = comparaVoltaAnterior.getPosicao();
				listaPodio.get(i - 1).setPosicao(listaPodio.get(i).getPosicao());
				listaPodio.get(i).setPosicao(auxPosicao);
				i = 0;
			} 
		}
		
		System.out.println("***************************");
		System.out.println("Classificação");
		System.out.println("***************************");
		
		System.out.println("***************************");
		System.out.println("Melhor Volta da Corrida: " + melhorVolta);
		System.out.println("***************************");
		for(int y = 0; y < listaPodio.size(); y++) {
			
			if(listaPodio.get(y).getPosicao() == ordem) {
				System.out.println("Posição: " + listaPodio.get(y).getPosicao());
				System.out.println("Piloto: " + listaPodio.get(y).getPiloto().getCodPiloto() + " - " + listaPodio.get(y).getPiloto().getNmePiloto());
				System.out.println("Voltas Completadas: " + listaPodio.get(y).getCorrida().getNumVolta());
				System.out.println("Tempo Total de Prova: " + listaPodio.get(y).getCorrida().getHrCorrida());
				System.out.println("Melhor Volta: " + listaPodio.get(y).getPiloto().getMelhorVolta());
				System.out.println("Velocidade Média: " + listaPodio.get(y).getPiloto().getVelocidadeMedia()/listaPodio.get(y).getCorrida().getNumVolta());
				
				if(ordem == 1) {
					tmpPrimeiro = listaPodio.get(y).getCorrida().getHrCorrida();
				} else {
					System.out.println("Diferença para o 1º colocado: " + -1 * listaPodio.get(y).getCorrida().getHrCorrida().until(tmpPrimeiro, MINUTES) + " Minutos e " + -1 * listaPodio.get(y).getCorrida().getHrCorrida().until(tmpPrimeiro, SECONDS) + " Segundos");
				}
				
				System.out.println("***************************");

				ordem++;
				y = -1;
			}
		}
	}
	
	public static void populaDados() {
		Piloto massa = new Piloto(38 , "F.MASSA");
		Piloto barrichello = new Piloto(33 , "R.BARRICHELLO");
		Piloto raikkonen = new Piloto(2 , "K.RAIKKONEN");
		Piloto webber = new Piloto(23 , "M.WEBBER");
		Piloto alonso = new Piloto(15 , "F.ALONSO");
		Piloto vettel = new Piloto(11 , "S.VETTEL");
		
		listaPodio.add(new Classificacao(1, massa));
		listaPodio.add(new Classificacao(2, barrichello));
		listaPodio.add(new Classificacao(3, raikkonen));
		listaPodio.add(new Classificacao(4, webber));
		listaPodio.add(new Classificacao(5, alonso));
		listaPodio.add(new Classificacao(6, vettel));
		
		listaVoltas.add(new Corrida(LocalTime.parse("23:49:08.277"), 1, "1:02.852", 44.275, massa));
		listaVoltas.add(new Corrida(LocalTime.parse("23:49:10.858"), 1, "1:04.352", 43.243, barrichello));
		listaVoltas.add(new Corrida(LocalTime.parse("23:49:11.075"), 1, "1:04.108", 43.408, raikkonen));
		listaVoltas.add(new Corrida(LocalTime.parse("23:49:12.667"), 1, "1:04.414", 43.202, webber));
		listaVoltas.add(new Corrida(LocalTime.parse("23:49:30.976"), 1, "1:18.456", 35.47, alonso));

		listaVoltas.add(new Corrida(LocalTime.parse("23:50:11.447"), 2, "1:03.170", 44.053, massa));
		listaVoltas.add(new Corrida(LocalTime.parse("23:50:14.860"), 2, "1:04.002", 43.48, barrichello));
		listaVoltas.add(new Corrida(LocalTime.parse("23:50:15.057"), 2, "1:03.982", 43.493, raikkonen));
		listaVoltas.add(new Corrida(LocalTime.parse("23:50:17.472"), 2, "1:04.805", 42.941, webber));
		listaVoltas.add(new Corrida(LocalTime.parse("23:50:37.987"), 2, "1:07.011", 41.528, alonso));

		listaVoltas.add(new Corrida(LocalTime.parse("23:51:14.216"), 3, "1:02.769", 44.334, massa));
		listaVoltas.add(new Corrida(LocalTime.parse("23:51:18.576"), 3, "1:03.716", 43.675, barrichello));
		listaVoltas.add(new Corrida(LocalTime.parse("23:51:19.044"), 3, "1:03.987", 43.49, raikkonen));
		listaVoltas.add(new Corrida(LocalTime.parse("23:51:21.759"), 3, "1:04.287", 43.287, webber));
		listaVoltas.add(new Corrida(LocalTime.parse("23:51:46.691"), 3, "1:08.704", 40.504, alonso));
		listaVoltas.add(new Corrida(LocalTime.parse("23:52:01.796"), 1, "3:31.315", 13.169, vettel));

		listaVoltas.add(new Corrida(LocalTime.parse("23:52:17.003"), 4, "1:02.787", 44.321, massa));
		listaVoltas.add(new Corrida(LocalTime.parse("23:52:22.586"), 4, "1:04.010", 43.474, barrichello));
		listaVoltas.add(new Corrida(LocalTime.parse("23:52:22.120"), 4, "1:03.076", 44.118, raikkonen));
		listaVoltas.add(new Corrida(LocalTime.parse("23:52:25.975"), 4, "1:04.216", 43.335, webber));
		listaVoltas.add(new Corrida(LocalTime.parse("23:53:06.741"), 4, "1:20.050", 34.763, alonso));
		listaVoltas.add(new Corrida(LocalTime.parse("23:53:39.660"), 2, "1:37.864", 28.435, vettel));
		listaVoltas.add(new Corrida(LocalTime.parse("23:54:57.757"), 3, "1:18.097", 35.633, vettel));
	}
	
	public static List<Corrida> lerArquivo() {
		 List<String> listaValores = new ArrayList<>();
		 List<Corrida> listaCorridaPrincipal = new ArrayList<>();
		 int posicao = 1;
		 Scanner ler = new Scanner(System.in);
		 
		    System.out.printf("Informe o nome de arquivo texto:\n");
		    String nome = ler.nextLine();
		 
		    System.out.printf("\nConteúdo do arquivo texto:\n");
		    try {
		      FileReader arq = new FileReader(nome);
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = lerArq.readLine(); // lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo
		// de repetição atingir o final do arquivo texto
		      linha = lerArq.readLine();
		      while (linha != null) {
		        String valoresLinha = "";
		        
		        for(int cont = 0; cont < linha.length(); cont++){
					char letra = linha.charAt(cont);
					
					if(letra != ' '){
						valoresLinha = valoresLinha + letra;
					}
					if(letra == ' ' || letra == '-'){
						if(!valoresLinha.isEmpty()) {
							listaValores.add(valoresLinha);
						}
						valoresLinha = "";
					}
					
					if(cont == (linha.length() - 1) && !valoresLinha.isEmpty()) {
						listaValores.add(valoresLinha);
						valoresLinha = "";
					}
				}
		        
		        String numVolta[] = listaValores.get(4).split("\\t");
		        String tmpVolta = "";
		        Double vlMedia = 0.0;
		        
		        if(numVolta.length == 3) {
		        	tmpVolta = numVolta[2];
		        	vlMedia = Double.parseDouble(listaValores.get(5).replaceAll(",", "."));
		        } else if (numVolta.length > 3){
		        	tmpVolta = numVolta[2];
		        	vlMedia = Double.parseDouble(numVolta[5].replaceAll(",", "."));
		        } else {
		        	tmpVolta = listaValores.get(5);
		        	vlMedia = Double.parseDouble(listaValores.get(6).replaceAll(",", "."));
		        }
		        Piloto piloto = new Piloto(Integer.parseInt(listaValores.get(1)), listaValores.get(3));
		        listaCorridaPrincipal.add(new Corrida(LocalTime.parse(listaValores.get(0)), Integer.parseInt(numVolta[0]), tmpVolta, vlMedia, piloto));
		        
		        if(listaCorridaPrincipal.get(listaCorridaPrincipal.size()-1).getNumVolta() == 1) {
		        	listaPodio.add(new Classificacao(posicao, piloto));
		        	posicao++;
		        }
		        
		        listaValores.clear();
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		      }
		 
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		 
		 return listaCorridaPrincipal; 
	}
}
