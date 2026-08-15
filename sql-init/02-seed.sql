-- Dataload local, só pra ter algo pra ver no catálogo e testar o fluxo
-- inteiro (inclusive acessibilidade, que não tem seed nenhum no repo).

INSERT INTO genres (name) VALUES
  ('Rock'), ('Pop'), ('Indie'), ('Eletrônica');

INSERT INTO artists (name, description) VALUES
  ('Billie Eilish', 'Cantora e compositora norte-americana.'),
  ('Arctic Monkeys', 'Banda de rock britânica formada em Sheffield.'),
  ('Michael Jackson', 'Cantor, compositor e dançarino norte-americano.'),
  ('The xx', 'Banda britânica de indie pop.'),
  ('Coldplay', 'Banda britânica de rock formada em Londres.'),
  ('Kings of Leon', 'Banda de rock americana formada por três irmãos e um primo.'),
  ('Placebo', 'Banda de rock britânica formada em Londres.');

-- Placeholder visível (ícone de vinil em SVG), já com o prefixo data:
-- completo — fonteDaImagem (assets/js/view/templates.js) usa esse valor
-- direto quando já vem prefixado. A API real manda JPEG puro sem prefixo.
INSERT INTO vinyls (title, price, description, released_at, image_url) VALUES
  ('Happier Than Ever', 150.00, 'Segundo álbum de estúdio de Billie Eilish.', '2021', 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyMDAgMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2VhZWFlYSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNzIiIGZpbGw9IiNiZGJkYmQiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjU4IiBmaWxsPSJub25lIiBzdHJva2U9IiNhOGE4YTgiIHN0cm9rZS13aWR0aD0iMSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNDQiIGZpbGw9Im5vbmUiIHN0cm9rZT0iI2E4YThhOCIgc3Ryb2tlLXdpZHRoPSIxIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSIzMCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjYThhOGE4IiBzdHJva2Utd2lkdGg9IjEiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjE0IiBmaWxsPSIjOGE4YThhIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSI0IiBmaWxsPSIjZWFlYWVhIi8+PC9zdmc+Cg=='),
  ('AM', 130.00, 'Quinto álbum de estúdio do Arctic Monkeys.', '2013', 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyMDAgMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2VhZWFlYSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNzIiIGZpbGw9IiNiZGJkYmQiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjU4IiBmaWxsPSJub25lIiBzdHJva2U9IiNhOGE4YTgiIHN0cm9rZS13aWR0aD0iMSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNDQiIGZpbGw9Im5vbmUiIHN0cm9rZT0iI2E4YThhOCIgc3Ryb2tlLXdpZHRoPSIxIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSIzMCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjYThhOGE4IiBzdHJva2Utd2lkdGg9IjEiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjE0IiBmaWxsPSIjOGE4YThhIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSI0IiBmaWxsPSIjZWFlYWVhIi8+PC9zdmc+Cg=='),
  ('Bad', 200.00, 'Sétimo álbum de estúdio de Michael Jackson.', '1987', 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyMDAgMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2VhZWFlYSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNzIiIGZpbGw9IiNiZGJkYmQiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjU4IiBmaWxsPSJub25lIiBzdHJva2U9IiNhOGE4YTgiIHN0cm9rZS13aWR0aD0iMSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNDQiIGZpbGw9Im5vbmUiIHN0cm9rZT0iI2E4YThhOCIgc3Ryb2tlLXdpZHRoPSIxIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSIzMCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjYThhOGE4IiBzdHJva2Utd2lkdGg9IjEiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjE0IiBmaWxsPSIjOGE4YThhIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSI0IiBmaWxsPSIjZWFlYWVhIi8+PC9zdmc+Cg=='),
  ('xx', 120.00, 'Álbum de estreia da banda The xx.', '2009', 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyMDAgMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2VhZWFlYSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNzIiIGZpbGw9IiNiZGJkYmQiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjU4IiBmaWxsPSJub25lIiBzdHJva2U9IiNhOGE4YTgiIHN0cm9rZS13aWR0aD0iMSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNDQiIGZpbGw9Im5vbmUiIHN0cm9rZT0iI2E4YThhOCIgc3Ryb2tlLXdpZHRoPSIxIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSIzMCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjYThhOGE4IiBzdHJva2Utd2lkdGg9IjEiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjE0IiBmaWxsPSIjOGE4YThhIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSI0IiBmaWxsPSIjZWFlYWVhIi8+PC9zdmc+Cg=='),
  ('X&Y', 130.00, 'Terceiro álbum de estúdio do Coldplay.', '2005', 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyMDAgMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2VhZWFlYSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNzIiIGZpbGw9IiNiZGJkYmQiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjU4IiBmaWxsPSJub25lIiBzdHJva2U9IiNhOGE4YTgiIHN0cm9rZS13aWR0aD0iMSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNDQiIGZpbGw9Im5vbmUiIHN0cm9rZT0iI2E4YThhOCIgc3Ryb2tlLXdpZHRoPSIxIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSIzMCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjYThhOGE4IiBzdHJva2Utd2lkdGg9IjEiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjE0IiBmaWxsPSIjOGE4YThhIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSI0IiBmaWxsPSIjZWFlYWVhIi8+PC9zdmc+Cg=='),
  ('Only by the Night', 130.00, 'Quarto álbum de estúdio do Kings of Leon.', '2008', 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyMDAgMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2VhZWFlYSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNzIiIGZpbGw9IiNiZGJkYmQiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjU4IiBmaWxsPSJub25lIiBzdHJva2U9IiNhOGE4YTgiIHN0cm9rZS13aWR0aD0iMSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNDQiIGZpbGw9Im5vbmUiIHN0cm9rZT0iI2E4YThhOCIgc3Ryb2tlLXdpZHRoPSIxIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSIzMCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjYThhOGE4IiBzdHJva2Utd2lkdGg9IjEiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjE0IiBmaWxsPSIjOGE4YThhIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSI0IiBmaWxsPSIjZWFlYWVhIi8+PC9zdmc+Cg=='),
  ('MTV Unplugged', 150.00, 'Álbum ao vivo do Placebo.', '2004', 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyMDAgMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2VhZWFlYSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNzIiIGZpbGw9IiNiZGJkYmQiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjU4IiBmaWxsPSJub25lIiBzdHJva2U9IiNhOGE4YTgiIHN0cm9rZS13aWR0aD0iMSIvPjxjaXJjbGUgY3g9IjEwMCIgY3k9IjEwMCIgcj0iNDQiIGZpbGw9Im5vbmUiIHN0cm9rZT0iI2E4YThhOCIgc3Ryb2tlLXdpZHRoPSIxIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSIzMCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjYThhOGE4IiBzdHJva2Utd2lkdGg9IjEiLz48Y2lyY2xlIGN4PSIxMDAiIGN5PSIxMDAiIHI9IjE0IiBmaWxsPSIjOGE4YThhIi8+PGNpcmNsZSBjeD0iMTAwIiBjeT0iMTAwIiByPSI0IiBmaWxsPSIjZWFlYWVhIi8+PC9zdmc+Cg==');

-- vinyl_artists (por título/nome, sem depender da ordem dos IDs)
INSERT INTO vinyl_artists (id_vinyl, id_artist)
SELECT v.id, a.id FROM vinyls v, artists a WHERE
  (v.title = 'Happier Than Ever' AND a.name = 'Billie Eilish') OR
  (v.title = 'AM' AND a.name = 'Arctic Monkeys') OR
  (v.title = 'Bad' AND a.name = 'Michael Jackson') OR
  (v.title = 'xx' AND a.name = 'The xx') OR
  (v.title = 'X&Y' AND a.name = 'Coldplay') OR
  (v.title = 'Only by the Night' AND a.name = 'Kings of Leon') OR
  (v.title = 'MTV Unplugged' AND a.name = 'Placebo');

-- vinyl_genres (Pop pra Billie, Rock pro resto, Indie também pra The xx)
INSERT INTO vinyl_genres (id_vinyl, id_genre)
SELECT v.id, g.id FROM vinyls v, genres g WHERE
  (v.title = 'Happier Than Ever' AND g.name = 'Pop') OR
  (v.title = 'AM' AND g.name = 'Rock') OR
  (v.title = 'Bad' AND g.name = 'Pop') OR
  (v.title = 'xx' AND g.name = 'Indie') OR
  (v.title = 'X&Y' AND g.name = 'Rock') OR
  (v.title = 'Only by the Night' AND g.name = 'Rock') OR
  (v.title = 'MTV Unplugged' AND g.name = 'Rock');

-- Catálogo de acessibilidade — nomes batem com os "rotulo" dos módulos em
-- assets/js/acessibility-features/, pra normalizar() casar certinho.
INSERT INTO accessibility (name, description) VALUES
  ('Leitor de tela', 'Lê em voz alta o que está em foco ou é clicado na página.'),
  ('Alto contraste', 'Aumenta o contraste de cores da interface.'),
  ('Texto aumentado', 'Aumenta o tamanho do texto da interface.'),
  ('Realce de cursor', 'Realça o elemento sob o cursor do mouse.'),
  ('Fonte para dislexia', 'Troca a tipografia por fontes recomendadas para leitura com dislexia.');
