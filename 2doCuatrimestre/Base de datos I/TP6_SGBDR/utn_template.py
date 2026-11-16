from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle
from reportlab.lib.enums import TA_CENTER, TA_LEFT
from reportlab.lib import colors
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont
from reportlab.platypus import (BaseDocTemplate, PageTemplate, Frame, Paragraph, Spacer,
                                PageBreak, NextPageTemplate, Table, TableStyle, Image, Preformatted)
from xml.sax.saxutils import escape

L = "/usr/share/fonts/truetype/liberation/"
for n, f in [("Ar", "LiberationSans-Regular"), ("Ar-B", "LiberationSans-Bold"),
             ("Ar-I", "LiberationSans-Italic"), ("Ar-BI", "LiberationSans-BoldItalic"),
             ("Mono", "LiberationMono-Regular"), ("Mono-B", "LiberationMono-Bold")]:
    pdfmetrics.registerFont(TTFont(n, L + f + ".ttf"))
pdfmetrics.registerFontFamily("Ar", normal="Ar", bold="Ar-B", italic="Ar-I", boldItalic="Ar-BI")
pdfmetrics.registerFont(TTFont("Star", "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf"))

W, H = A4
M = 72
S = dict(
    body=ParagraphStyle("body", fontName="Ar", fontSize=11, leading=19, firstLineIndent=36, alignment=TA_LEFT),
    flat=ParagraphStyle("flat", fontName="Ar", fontSize=11, leading=19),
    h1=ParagraphStyle("h1", fontName="Ar", fontSize=14, leading=19, spaceBefore=26, spaceAfter=18),
    sub=ParagraphStyle("sub", fontName="Ar-BI", fontSize=11, leading=19, spaceBefore=8),
    consigna=ParagraphStyle("consigna", fontName="Ar-BI", fontSize=11, leading=19, leftIndent=18, spaceBefore=14),
    bullet=ParagraphStyle("bullet", fontName="Ar", fontSize=11, leading=19, leftIndent=36, bulletIndent=18, bulletFontName="Star", bulletFontSize=7),
    num=ParagraphStyle("num", fontName="Ar", fontSize=11, leading=19, leftIndent=36, bulletIndent=18),
    cell=ParagraphStyle("cell", fontName="Ar", fontSize=11, leading=14),
    cellh=ParagraphStyle("cellh", fontName="Ar-BI", fontSize=11, leading=14, alignment=TA_CENTER),
    code=ParagraphStyle("code", fontName="Mono", fontSize=10, leading=12.4),
)

def esc(t):
    parts = escape(t).split("**")
    return "".join(("<b><i>%s</i></b>" if i % 2 else "%s") % p for i, p in enumerate(parts))

class Doc:
    def __init__(self, path):
        self.path, self.f = path, []
        self.doc = BaseDocTemplate(path, pagesize=A4, leftMargin=M, rightMargin=M, topMargin=M, bottomMargin=M)
        self.doc.addPageTemplates([PageTemplate(id="p", frames=[Frame(M, M, W - 2 * M, H - 2 * M, 0, 0, 0, 0)])])

    def cover(self, tipo, tema, materia, autores, docentes, fecha=None):
        def draw(c, d):
            def y(top, size): return H - top - size * 0.905
            c.setFont("Ar", 14); c.drawCentredString(W / 2, y(107, 14), tipo)
            c.setFont("Ar", 20); c.drawCentredString(W / 2, y(132, 20), tema)
            c.setFont("Ar", 14); c.drawCentredString(W / 2, y(165, 14), materia)
            c.setFont("Ar", 12)
            for i, a in enumerate(reversed(autores)): c.drawString(M, y(666 - 33 * i, 12), a)
            c.drawString(M, y(731, 12), "Profesor/a: " + docentes)
            if fecha: c.drawString(M, y(753, 12), "Fecha de entrega: " + fecha)
        self.doc.addPageTemplates([PageTemplate(id="cover", frames=[Frame(M, M, W - 2 * M, H - 2 * M)], onPage=draw)])
        self.cover_drawn = True
        self.f += [NextPageTemplate("p"), PageBreak()]

    def h1(self, t): self.f.append(Paragraph(esc(t), S["h1"]))
    def sub(self, t): self.f.append(Paragraph(esc(t), S["sub"]))
    def p(self, t, indent=True): self.f.append(Paragraph(esc(t), S["body"] if indent else S["flat"]))
    def consigna(self, t): self.f.append(Paragraph('<font name="Star">★</font> ' + esc(t), S["consigna"]))
    def bullets(self, items):
        for t in items: self.f.append(Paragraph(esc(t), S["bullet"], bulletText="●"))
    def numbered(self, items, start=1):
        for i, t in enumerate(items, start): self.f.append(Paragraph(esc(t), S["num"], bulletText=f"{i}."))
    def label(self, t): self.f.append(Paragraph(esc(t), S["sub"]))

    def table(self, header, rows, widths):
        data = [[Paragraph(esc(h), S["cellh"]) for h in header]] + \
               [[Paragraph(esc(c), S["cell"]) for c in r] for r in rows]
        t = Table(data, colWidths=widths)
        t.setStyle(TableStyle([("GRID", (0, 0), (-1, -1), 0.75, colors.black), ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
                               ("TOPPADDING", (0, 0), (-1, -1), 5), ("BOTTOMPADDING", (0, 0), (-1, -1), 5)]))
        self.f += [t, Spacer(1, 14)]

    def code(self, text):
        pre = Preformatted(text.strip("\n"), S["code"])
        t = Table([[pre]], colWidths=[W - 2 * M])
        t.setStyle(TableStyle([("BACKGROUND", (0, 0), (-1, -1), colors.HexColor("#efefef")),
                               ("LEFTPADDING", (0, 0), (-1, -1), 8), ("TOPPADDING", (0, 0), (-1, -1), 6),
                               ("BOTTOMPADDING", (0, 0), (-1, -1), 6)]))
        self.f += [t, Spacer(1, 12)]

    def image(self, path, width=None):
        from reportlab.lib.utils import ImageReader
        iw, ih = ImageReader(path).getSize()
        w = min(width or (W - 2 * M), W - 2 * M)
        self.f += [Image(path, w, w * ih / iw, hAlign="CENTER"), Spacer(1, 12)]

    def build(self):
        if getattr(self, "cover_drawn", False):
            self.doc.pageTemplates.insert(0, self.doc.pageTemplates.pop())
        self.doc.build(self.f)
